Для разработки я использовал
Java 17 (На 8-й тоже должно работать)
ApacheTomcat 9.0.62 
javax.servlet version:3.1.0
org.json version:20220320

intellij IDEA:
File -> New -> Project from Version Control
Version control ->Git
URL - > https://github.com/VadimZineev/Test_Spimex_Api.git 

Настройка конфигурации:
Add Configuration -> Add new -> Tomcar Server -> Local 
	В Application server выбрать путь к ApacheTomcat (ссылка для скачивания: https://tomcat.apache.org/download-90.cgi)
	JRE по дефолту
Открыть вкладку Deployment -> Добавить в Deploy at the server startup -> Artifact -> Test_Spimex_Api:war exploded -> Ok
Apply -> Ok
Запустить

Автоматически откроется страница в браузере. Если нет, то перейти по адресу http://localhost:8080/Test_Spimex_Api_war_exploded/
Ввести номер региона, нажать Start. Появится число(количество активных записей). 
Для повторного использования необходимо нажать в браузере кнопку назад 