import logging

# Configuração do logging
logging.basicConfig (
    filename="test.log",
    level=logging.INFO,
    format="%(asctime)s - [%(levelname)s] %(message)s",
    filemode='w' 
)

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