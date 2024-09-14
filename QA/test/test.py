import logging
import os

directory = "./log/"
if not os.path.exists(directory):
    os.makedirs(directory)

# Cria um logger
logger = logging.getLogger()
logger.setLevel(logging.NOTSET)

# Formato da resposta
format = logging.Formatter("%(asctime)s - [%(levelname)s] %(message)s")

info_handler = logging.FileHandler("./log/info.log", mode="w")
info_handler.setLevel(logging.INFO)
info_handler.setFormatter(format)
logger.addHandler(info_handler)

debug_handler = logging.FileHandler("./log/debug.log", mode="w")
debug_handler.setLevel(logging.DEBUG)
debug_handler.setFormatter(format)
logger.addHandler(debug_handler)

warning_handler = logging.FileHandler("./log/warning.log", mode="w")
warning_handler.setLevel(logging.WARNING)
warning_handler.setFormatter(format)
logger.addHandler(warning_handler)

error_handler = logging.FileHandler("./log/error.log", mode="w")
error_handler.setLevel(logging.ERROR)
error_handler.setFormatter(format)
logger.addHandler(error_handler)

logger = logging.getLogger(__name__)

def info(message):
    logger.info(message)

def debug(message):
    logger.debug(message)

def warning(message):
    logger.warning(message)

def error(message):
    logger.error(message)

def critical(message):
    logger.critical(message)