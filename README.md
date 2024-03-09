# Communication Boost

## О проекте

**Предметная область:**

Компания, желающая наладить удобную коммуникацию с клиентами и сотрудниками.

**Проблема:**

Отсутствует бесплатное решение для установки удобной коммуникации с клиентами и сотрудниками компании.

**Решение:**

Разработка телеграм-бота с определенной функциональностью.

**Функциональность телеграм-бота:**

Основные возможности проекта описаны в [документации API](docs/API.md), включая поддержку ролей "Администратор", "Сотрудник" и "Клиент".
Каждый модуль представляет собой  spring boot starter.

## Технологии

- **Spring Boot Starter Web**:
- **Spring Boot Starter Actuator**
- **Spring Boot Starter Mail**
- **Spring Boot Starter Validation**
- **Lombok**
- **Ok http**
- **Telegram API**
- **Spring Dotenv**
- **AspectJ Weaver**
 
## Субд
- **Redis**
- **Postgres**
- 
Перед запуском необходимо создать файл .env и заполнить следующие переменные окружения
- BOT_KEY
- BOT_USERNAME
- MAIL_DEBUG
- MAIL_HOST
- MAIL_PORT
- MAIL_USERNAME
- MAIL_PASSWORD
- MAIL_PROTOCOL
- REDIS_HOST
- REDIS_PORT
- REDIS_DURATION
- REDIS_PASSWORD
- GENERATE_CODE_ALGORITHM
- GENERATE_CODE_STRENGTH