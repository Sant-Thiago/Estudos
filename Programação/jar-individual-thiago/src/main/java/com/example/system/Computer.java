package com.example.system;

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

import com.example.service.Formatar;

import oshi.SystemInfo;
import oshi.hardware.NetworkIF;

public class Computer {

    private static Boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
    private String directory = "src\\main\\java\\com\\example\\scripts";

    private ProcessBuilder processBuilder;
    private String hostname;
    private String mac;
    private String ipv4;

    private Formatar formatar = new Formatar();

    public String getHostname() {
        try {

            String[] command = makeCommand("userInfo.cmd");

            processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            List<String> listStrings = new ArrayList<>();
            
            String line;
            
            while ((line = reader.readLine()) != null) {
                listStrings.add(line);
            }

            BufferedReader erroReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            while((line = erroReader.readLine()) != null) {
                System.err.println("Saida de erro: "+ line);
            }

            this.hostname = listStrings.get(2);

            return this.hostname;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public String getIpv4() {
        try {
            String command[];

            if (!isWindows) {
                String scriptPath = directory + "\\bash\\ipv4.sh";
                command = formatar.commandBash(scriptPath);
            } else {
                String scriptPath = directory + "\\cmd\\ipv4.cmd";
                command = formatar.commandCommandPrompt(scriptPath);
            }
            
            processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            List<String> listStrings = new ArrayList<>();

            String line;

            while((line = reader.readLine()) != null) {
                listStrings.add(line);
            }

            BufferedReader erroReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        
            while ((line = erroReader.readLine()) != null) {
                System.err.println("Saída de erro: "+ line);
            }

            String ipv4 = listStrings.get(2);
            
            if (isWindows) {
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

            this.ipv4 = ipv4;

            return ipv4;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getNetwork() {
        List<String> listaIps = new ArrayList<>();

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                if (iface.isLoopback() || !iface.isUp()) continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    String ip = addr.getHostAddress();
                    System.out.println(iface.getDisplayName() + " " + ip);
                    listaIps.add(ip);
                }
            }


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

    public Float getTransmissaoRede() {

        List<String> listResults = getRede();

        Optional<String> transmissao = listResults.stream()
            .filter(result -> result.toLowerCase().contains("transmiss"))
                .map(result -> result.split(":")[1].trim())
                    .findFirst();

        return Float.parseFloat(transmissao.get());
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
    
        this.mac = enderecosMAC.get(0);

        return enderecosMAC.get(0);
    }

    public List<List<String>> listarDispositivos(String ip) {
        List<List<String>> listAddress = new ArrayList<>();

        Pattern pattern = Pattern.compile("^.*(?=\\.\\d+$)");
        Matcher matcher = pattern.matcher(ip);
        matcher.find();
        String newIp = matcher.group();
        
        try {
            List<String> listResults = mostrarResultado("listDevices.cmd");

            listResults = listResults.stream()
                .filter(result -> result.contains(newIp))
                    .collect(Collectors.toList());

            listResults.remove(0);
            listResults.remove(listResults.size()-1);

            for (String result : listResults) {
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
        List<String> listResults = new ArrayList<>();
        
        try {
            listResults = mostrarResultado("rede.cmd");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listResults;
    }

    private String[] makeCommand(String file) {
        String[] command;

        if (!isWindows) {
            String scriptPath = directory + "\\bash\\"+ file;
            command = formatar.commandBash(scriptPath);
        } else {
            String scriptPath = directory + "\\cmd\\"+ file;
            command = formatar.commandCommandPrompt(scriptPath); 
        }

        return command;
    }

    private List<String> mostrarResultado(String scriptName) {
        
        List<String> listResults = new ArrayList<>();
        try {

            InputStream inputStream = getClass().getResourceAsStream("/cmd/" + scriptName);
            if (inputStream == null) {
				throw new IOException("Arquivo BAT não encontrado no recurso.");
			}

            Path tempFile = Files.createTempFile("temp_script", isWindows ? ".cmd" : ".sh");
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        
            String absolutePath = tempFile.toAbsolutePath().toString();
        
            Process processo = Runtime.getRuntime().exec(absolutePath);

            BufferedReader reader = new BufferedReader(new InputStreamReader(processo.getInputStream()));
    
            String line;
    
            while ((line = reader.readLine()) != null) {
                listResults.add(line);
            }
    
            BufferedReader erroReader = new BufferedReader(new InputStreamReader(processo.getErrorStream()));
    
            while ((line = erroReader.readLine()) != null) {
                System.err.println("Saída de erro: "+ line);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return listResults;

    }

}
