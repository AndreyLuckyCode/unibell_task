Константы в контроллере должны быть вынесены в application.properties, оставил для наглядности при просмотре кода, дублирую эндпоинты здесь:

@RequestMapping("/api")

Создание клиента 
(POST)
http://localhost:8080/api/clients

Добавление контактной информации клиента (почта/номер телефона)
(PATCH)
http://localhost:8080/api/clients/{client_id}/add_info

Список всех клиентов
(GET)
http://localhost:8080/api/clients/all

Список контакной информации конкретного клиента
(GET)
http://localhost:8080/api/clients/{client_id}/info

Список email конкретного клиента
(GET)
http://localhost:8080/api/clients/{client_id}/email

Список телефонных номеров конкретного клиента
(GET)
http://localhost:8080/api/clients/{client_id}/phone




