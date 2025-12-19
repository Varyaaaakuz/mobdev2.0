Первым техническим шагом стала подготовка проекта к использованию Navigation Component. В файле сборки модуля app (build.gradle) были добавлены необходимые библиотеки. Библиотека navigation-fragment предоставляет базовые классы для работы с навигацией через фрагменты, а navigation-ui содержит дополнительные компоненты для интеграции с элементами Material Design, такими как BottomNavigationView. Одновременно был активирован View Binding через buildFeatures { viewBinding true }, что позволило отказаться от использования findViewById() и обеспечить типобезопасный доступ к элементам пользовательского интерфейса. После внесения изменений в build.gradle была выполнена синхронизация проекта в Android Studio для загрузки указанных библиотек.

<img width="887" height="662" alt="image" src="https://github.com/user-attachments/assets/3b01449b-abf7-40ae-812f-6c286f7b344e" />

Центральным элементом Navigation Component является граф навигации - XML-файл, описывающий все экраны приложения и возможные переходы между ними. В проекте была создана новая папка ресурсов res/navigation, в которой размещен файл nav_graph.xml. Граф определяет HomeFragment как стартовую точку навигации (startDestination) - это первый экран, который видит пользователь после авторизации. Всего в графе описано пять пунктов назначения, соответствующих основным разделам приложения: главный экран со списком пород, детальная информация о породе, советы по уходу, экран поиска и профиль пользователя. Каждый пункт назначения содержит идентификатор, имя класса фрагмента и метку для отображения в UI.

<img width="978" height="902" alt="image" src="https://github.com/user-attachments/assets/986cb6b8-b3bb-4341-8aa3-6be795ee187a" />

Основная активность приложения была полностью переработана. Вместо старой реализации, где MainActivity одновременно управлял UI и навигацией, создана новая версия, действующая как контейнер для Navigation Component. Активность теперь выполняет минимальный набор функций: инициализация ViewModel, проверка аутентификации пользователя и настройка навигационного контроллера. Вся бизнес-логика работы со списком пород перенесена в HomeFragment, что соответствует принципу единственной ответственности
<img width="966" height="779" alt="image" src="https://github.com/user-attachments/assets/0d4efb09-b5bf-420d-92b0-c56d4a3c6b09" />
<img width="1207" height="862" alt="image" src="https://github.com/user-attachments/assets/667404a1-d7ae-447c-b39f-52dfae7c91d2" />

Был создан новый файл макета activity_main_nav.xml, который заменил старый activity_main.xml. Основным элементом макета стал FragmentContainerView с атрибутом app:navGraph, связывающим контейнер с графом навигации. NavHostFragment выступает в роли хоста, который автоматически управляет переключением фрагментов в соответствии с текущим пунктом назначения в графе. Нижняя панель навигации размещена под контейнером фрагментов.

<img width="830" height="712" alt="image" src="https://github.com/user-attachments/assets/8e1b0a12-002a-4707-9d31-7b7536ecf338" />

Наиболее сложной задачей было создание HomeFragment - нового главного экрана приложения, куда была перенесена вся функциональность по отображению и поиску пород собак. Фрагмент был реализован с использованием View Binding, что обеспечило безопасный доступ к элементам UI. Вся бизнес-логика работы с данными вынесена в MainViewModel, который используется совместно с другими фрагментами. Навигация реализована через NavController, полученный в методе onViewCreated().
<img width="1030" height="888" alt="image" src="https://github.com/user-attachments/assets/04f4d1fa-9b0c-4053-b164-3f5a1b923269" />
<img width="938" height="781" alt="image" src="https://github.com/user-attachments/assets/9270ec3a-2d44-4972-85c3-be7649136e85" />
<img width="1073" height="940" alt="image" src="https://github.com/user-attachments/assets/e3749e57-5a08-40ad-8f90-40a69b154481" />

Все существующие фрагменты приложения были модернизированы для использования View Binding. Для каждого фрагмента создан соответствующий класс Binding (например, FragmentBreedDetailsBinding для BreedDetailsFragment), который предоставляет прямой доступ ко всем элементам UI, определенным в XML-макете. Это не только упростило код, но и повысило его безопасность - ошибки обращения к несуществующим элементам теперь обнаруживаются на этапе компиляции, а не во время выполнения.
<img width="990" height="915" alt="image" src="https://github.com/user-attachments/assets/b3a114c6-a0a0-4322-a4c1-510240a7ddfd" />

Нижняя панель навигации была настроена для работы с Navigation Component через утилиту NavigationUI. Файл меню bottom_navigation.xml был обновлен - идентификаторы пунктов меню теперь соответствуют идентификаторам пунктов назначения в графе навигации. NavigationUI автоматически синхронизирует состояние BottomNavigationView с текущим пунктом назначения в NavController, выделяя активный элемент и обрабатывая нажатия для перехода между экранами.
<img width="674" height="576" alt="image" src="https://github.com/user-attachments/assets/6bab1725-ff96-49f4-83ea-3373a1f4dce1" />

Результат работы:
<img width="418" height="836" alt="image" src="https://github.com/user-attachments/assets/6fa1cceb-4c6a-46e8-bae9-f60b8feedf57" /> <img width="382" height="825" alt="image" src="https://github.com/user-attachments/assets/de50fcaf-e19c-49fa-a945-9c458cb7023f" /> <img width="409" height="829" alt="image" src="https://github.com/user-attachments/assets/2b6639c8-e62a-4643-817d-38c0046164c5" />  <img width="396" height="826" alt="image" src="https://github.com/user-attachments/assets/9a2b661d-45a2-4a8d-b264-c33d072bae7b" />



