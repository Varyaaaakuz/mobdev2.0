Создан новый модуль с именем BottomNavigation. В файле build.gradle активирован View Binding и добавлены необходимые зависимости Navigation Component. View Binding включен для безопасного доступа к элементам интерфейса без использования findViewById().

<img width="804" height="309" alt="image" src="https://github.com/user-attachments/assets/1d73afd4-3354-42b8-a399-645b4d5a00e1" />

Созданы три фрагмента: HomeFragment, SearchFragment и ProfileFragment. Каждый фрагмент содержит простой макет с текстовым представлением и реализован с использованием View Binding. Для каждого фрагмента созданы соответствующие классы Binding, автоматически сгенерированные системой на основе XML-макетов.

<img width="1002" height="794" alt="image" src="https://github.com/user-attachments/assets/86bc5b54-9caf-4727-a134-7ed0fc7e46f4" />

Далее был создан файл nav_graph.xml, содержащий описание всех пунктов назначения приложения. Граф определяет связи между фрагментами и устанавливает HomeFragment как стартовый пункт.

<img width="1088" height="735" alt="image" src="https://github.com/user-attachments/assets/2e2ca670-d654-4c0d-a648-879250a012b5" />

Создан макет activity_main.xml, содержащий FragmentContainerView для отображения фрагментов и BottomNavigationView для навигации. FragmentContainerView настроен с атрибутом app:navGraph, который связывает его с созданным графом навигации, обеспечивая автоматическое управление переходами.

<img width="1020" height="846" alt="image" src="https://github.com/user-attachments/assets/59ec4e48-3e19-46b9-92f9-5c5cd4a57386" />

Создан файл bottom_nav_menu.xml в директории res/menu/, содержащий три пункта меню с иконками и текстовыми метками. Идентификаторы пунктов меню соответствуют идентификаторам фрагментов в графе навигации.

В MainActivity реализована инициализация навигационного контроллера и связь с BottomNavigationView. Использован метод NavigationUI.setupWithNavController(), который автоматически синхронизирует состояние нижней панели навигации с текущим пунктом назначения в графе.

<img width="1155" height="724" alt="image" src="https://github.com/user-attachments/assets/6af12696-48cc-4673-86b0-b56495fc13b7" />

Результат работы:

<img width="389" height="799" alt="image" src="https://github.com/user-attachments/assets/535e82cc-bb66-4fe6-9f2c-9caaaa0e35fb" /> <img width="394" height="803" alt="image" src="https://github.com/user-attachments/assets/870145f1-955f-45ee-ab19-26ffb69cbfdb" /> <img width="368" height="798" alt="image" src="https://github.com/user-attachments/assets/c41798e6-6edd-4022-95f1-bdb882984390" />

Создан отдельный модуль NavigationDrawer с аналогичными зависимостями. Особенностью данного модуля является необходимость использования DrawerLayout в качестве корневого элемента макета, который обеспечивает функциональность выдвижной панели навигации

Создан граф навигации, аналогичный первому модулю, но с учетом особенностей Navigation Drawer. Граф включает те же три пункта назначения, но структура меню и способ взаимодействия с пользователем отличаются.

Далее был создан макет, состоящий из DrawerLayout, содержащего основной контент и NavigationView. Основной контент включает Toolbar с кнопкой и контейнер для фрагментов. NavigationView настроен с атрибутом app:headerLayout для отображения заголовка шторки.

<img width="996" height="818" alt="image" src="https://github.com/user-attachments/assets/ee407970-21cc-4151-8674-2d51fa30c2b5" />

Была создана активность MainActivity. Активность реализует паттерн single-activity architecture, где все переходы между экранами обрабатываются через Navigation Component, а боковая шторка навигации обеспечивает доступ к основным разделам приложения

<img width="1163" height="900" alt="image" src="https://github.com/user-attachments/assets/cf7a7d8d-6eae-4c0c-a0e5-70a96950db26" />

Результат работы:

<img width="374" height="790" alt="image" src="https://github.com/user-attachments/assets/95e8d22b-37cd-4c23-a465-195843160396" /> <img width="365" height="805" alt="image" src="https://github.com/user-attachments/assets/28e3d5c4-4a41-433b-ab8b-b27a3ea5684d" /> <img width="371" height="801" alt="image" src="https://github.com/user-attachments/assets/1bf2f1b2-35ff-4c0b-a5b3-a9533dabfb1c" /> <img width="387" height="799" alt="image" src="https://github.com/user-attachments/assets/11a606d4-df17-449b-a5fb-159cee252d25" />

BottomNavigationApp представляет собой оптимальное решение для приложений с небольшим количеством основных экранов, требующих быстрого переключения между ними. NavigationDrawerApp подходит для приложений с более сложной структурой, где навигационные элементы могут быть скрыты до момента необходимости.

В файле сборки модуля app были добавлены необходимые библиотеки. Библиотека navigation-fragment предоставляет базовые классы для работы с навигацией через фрагменты, а navigation-ui содержит дополнительные компоненты для интеграции с элементами Material Design, такими как BottomNavigationView. Одновременно был активирован View Binding.  

<img width="887" height="662" alt="image" src="https://github.com/user-attachments/assets/3b01449b-abf7-40ae-812f-6c286f7b344e" />

Центральным элементом Navigation Component является граф навигации - XML-файл, описывающий все экраны приложения и возможные переходы между ними. В проекте была создана новая папка ресурсов, в которой размещен файл nav_graph.xml. Граф определяет HomeFragment как стартовую точку навигации. Всего в графе описано пять пунктов назначения, соответствующих основным разделам приложения: главный экран со списком пород, детальная информация о породе, советы по уходу, экран поиска и профиль пользователя. Каждый пункт назначения содержит идентификатор, имя класса фрагмента и метку для отображения в UI.

<img width="978" height="902" alt="image" src="https://github.com/user-attachments/assets/986cb6b8-b3bb-4341-8aa3-6be795ee187a" />

Основная активность приложения была полностью переработана. Вместо старой реализации, где MainActivity одновременно управлял UI и навигацией, создана новая версия, действующая как контейнер для Navigation Component. Активность теперь выполняет минимальный набор функций: инициализация ViewModel, проверка аутентификации пользователя и настройка навигационного контроллера. Вся бизнес-логика работы со списком пород перенесена в HomeFragment.

<img width="966" height="779" alt="image" src="https://github.com/user-attachments/assets/0d4efb09-b5bf-420d-92b0-c56d4a3c6b09" />
<img width="1207" height="862" alt="image" src="https://github.com/user-attachments/assets/667404a1-d7ae-447c-b39f-52dfae7c91d2" />

Был создан новый файл макета activity_main_nav.xml, который заменил старый activity_main.xml. Основным элементом макета стал FragmentContainerView, связывающим контейнер с графом навигации.  выступает в роли хоста, который автоматически управляет переключением фрагментов в соответствии с текущим пунктом назначения в графе. Нижняя панель навигации размещена под контейнером фрагментов.

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



