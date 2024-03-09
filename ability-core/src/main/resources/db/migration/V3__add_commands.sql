INSERT INTO commands (command_id, command_info, command_url, required_role)
    VALUES (1, 'Получить информацию о команде', '/get_command_info', 'ROLE_CLIENT');
INSERT INTO commands (command_id, command_info, command_url, required_role)
    VALUES (2, 'Отправить сообщение пользователям по указанным email', '/send_message_by_email', 'ROLE_EMPLOYEE');
INSERT INTO commands (command_id, command_info, command_url, required_role)
    VALUES (3, 'Отправить сообщение всем пользователям с указанной ролью', '/send_message_by_role', 'ROLE_EMPLOYEE');
INSERT INTO commands (command_id, command_info, command_url, required_role)
    VALUES (4, 'Одобрить указанный запрос на бронирование товара', '/accept_product_request', 'ROLE_EMPLOYEE');
INSERT INTO commands (command_id, command_info, command_url, required_role)
    VALUES (5, 'Отменить указанный запрос на бронирование товара', '/cancel_product_request', 'ROLE_CLIENT');
INSERT INTO commands (command_id, command_info, command_url, required_role)
    VALUES (6, 'Отправить запрос на бронирование товара', '/send_product_request', 'ROLE_CLIENT');
INSERT INTO commands (command_id, command_info, command_url, required_role)
    VALUES (7, 'Посмотреть все текущие запросы на бронирование товара', '/view_product_requests', 'ROLE_CLIENT');
INSERT INTO commands (command_id, command_info, command_url, required_role)
    VALUES (8, 'Получить информацию об указанном товаре', '/get_product_info', 'ROLE_CLIENT');
INSERT INTO commands (command_id, command_info, command_url, required_role)
    VALUES (9, 'Заблокировать пользователя', '/block_user', 'ROLE_ADMIN');
INSERT INTO commands (command_id, command_info, command_url, required_role)
    VALUES (10, 'Разблокировать пользователя', '/unblock_user', 'ROLE_ADMIN');
INSERT INTO commands (command_id, command_info, command_url, required_role)
    VALUES (11, 'Изменить роль пользователя', '/change_role', 'ROLE_ADMIN');
INSERT INTO commands (command_id, command_info, command_url, required_role)
    VALUES (12, 'Начать работу с ботом', '/start', 'ROLE_CLIENT');
