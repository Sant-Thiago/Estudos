package app.integration;

import static com.sun.jna.Platform.isWindows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import oshi.SystemInfo;
import oshi.hardware.NetworkIF;

public class Computer {

	private static Boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

	public String getUsername() {
		try {
			String scriptName = isWindows ? "userUsername.cmd" : "userUsername.sh";

			String absolutePath = createPath(scriptName);
			
			List<String> listStrings = lerTerminal(absolutePath);

			return listStrings.get(2);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getHostname() {
		try {
			String scriptName = isWindows ? "userHostname.cmd" : "hostname.sh";

			String absolutePath = createPath(scriptName);

			List<String> listStrings = lerTerminal(absolutePath);

			return listStrings.get(2);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getIpv4() {
        try {
			String scriptName = isWindows ? "ipv4.cmd" : "ipv4.sh";

			String absolutePath = createPath(scriptName);

			List<String> listStrings = lerTerminal(absolutePath);

            String ipv4 = listStrings.get(listStrings.size() - 1);


			if (isWindows()) {
				Boolean doisPontos = false;
	
				StringBuilder sb = new StringBuilder();
	
				for (int i = 0; i < ipv4.length(); i++) {
					if (ipv4.charAt(i) == ':' || doisPontos) {
						doisPontos = true;
	
						char c = ipv4.charAt(i);
				
						if (Character.isDigit(c) || c == '.') {
							sb.append(c);
						}
					}
				}
				ipv4 = sb.toString();
			}

			return ipv4;


        } catch (Exception e) {
            e.printStackTrace();
		}

		return null;
	}

	public List<String> getNetwork() {
		try {
			List<String> listaIps = new ArrayList<>();
            
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                if (iface.isLoopback() || !iface.isUp()) continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    String ip = addr.getHostAddress();
                    listaIps.add(ip);
                }
            }

			return listaIps;

        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

	public String getNomeRede() {

        List<String> listResults = getRede();

        Optional<String> nome = listResults.stream()
            .filter(result -> result.toLowerCase().contains("ssid"))
                .map(result -> result.split(":")[1].trim())
                    .findFirst();

        return nome.get();
    }

    public String getInterfaceRede() {

        List<String> listResults = getRede();

        Optional<String> interfaceRede = listResults.stream()
            .filter(result -> result.toLowerCase().contains("nome"))
                .map(result -> result.split(":")[1].trim())
                    .findFirst();

        return interfaceRede.get();
    }

    public Integer getSinalRede() {

        List<String> listResults = getRede();

        Optional<String> sinal = listResults.stream()
            .filter(result -> result.toLowerCase().contains("sinal"))
                .map(result -> result.split(":")[1].replace("%", "").trim())
                    .findFirst();

        return Integer.parseInt(sinal.get());
    }

    public Double getTransmissaoRede() {

        List<String> listResults = getRede();

        Optional<String> transmissao = listResults.stream()
            .filter(result -> result.toLowerCase().contains("transmiss"))
                .map(result -> result.split(":")[1].trim())
                    .findFirst();

        return Double.parseDouble(transmissao.get());
    }

    public String getBssidRede() {
        
        List<String> listResults = getRede();

        Optional<String> BSSID = listResults.stream()
            .filter(result -> result.toLowerCase().contains("bssid"))
                .map(result -> result.split(": ")[1].trim())
                    .findFirst();

        return BSSID.get();
    }

    public String getMac() {
        SystemInfo systemInfo = new SystemInfo();
        List<NetworkIF> networkIFs = systemInfo.getHardware().getNetworkIFs();

        List<String> enderecosMAC = new ArrayList<>();

        for (NetworkIF net : networkIFs) {
            enderecosMAC.add(net.getMacaddr());
        }

        return enderecosMAC.get(0);
    }

    public List<List<String>> listarDispositivos(String ip) {
        List<List<String>> listAddress = new ArrayList<>();

        Pattern pattern = Pattern.compile("^.*(?=\\.\\d+$)");
        Matcher matcher = pattern.matcher(ip);
        matcher.find();
        String newIp = matcher.group();
        
        try {
            String absolutePath = createPath("listDevices.cmd");

			List<String> listStrings = lerTerminal(absolutePath);

            listStrings = listStrings.stream()
                .filter(result -> result.contains(newIp))
                    .collect(Collectors.toList());


            listStrings.remove(listStrings.size()-1);

            for (String result : listStrings) {
                String[] parts = result.trim().split("\\s+");
                String ipAd = parts[0];
                String macAd = parts[1];

                List<String> miniListIpMac = new ArrayList<>();
                miniListIpMac.add(ipAd);
                miniListIpMac.add(macAd);

                listAddress.add(miniListIpMac);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listAddress;
    }

    private List<String> getRede() {        
        List<String> listStrings = new ArrayList<>();
		
		try {
            String absolutePath = createPath("rede.cmd");
            
			listStrings = lerTerminal(absolutePath);
        } catch (Exception e) {
			e.printStackTrace();
        }
		
		return listStrings;
    }
	
	private String createPath(String scriptName) {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/scripts" + (isWindows ? "/cmd/" : "/bash/") + scriptName);
			
			if (inputStream == null) {
				throw new IOException("Arquivo BAT não encontrado no recurso.");
			}

			Path tempFile = Files.createTempFile("temp_script", isWindows ? ".bat" : ".sh");
			Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

			String path = tempFile.toAbsolutePath().toString();

			return path;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao criar um caminho para executar os comandos!", e);
		}
	}

	private List<String> lerTerminal(String absolutePath) {		
		try {
			List<String> listStrings = new ArrayList<>();
			
			Process processo = Runtime.getRuntime().exec(absolutePath);
	
			BufferedReader reader = new BufferedReader(new InputStreamReader(processo.getInputStream()));
			
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				listStrings.add(line);
			}
	
			BufferedReader erroReader = new BufferedReader(new InputStreamReader(processo.getErrorStream()));
			
			while ((line = erroReader.readLine()) != null) {
				System.err.println("Saída de erro: "+ line);
			}

			return listStrings;
				
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao ler os resultados vindos do terminal!", e);
		}
		
	}
	
}