Был создан новый модуль FragmentApp и были настроены зависимости в файле build.gradle, где была добавлена библиотека androidx.fragment для работы с фрагментами. В главной активности MainActivity была реализована проверка состояния savedInstanceState через условие if - это обеспечило, что фрагмент добавлялся только при первоначальном запуске приложения, а не при каждом его пересоздании. Для передачи данных был использован объект Bundle, в который был помещен номер студента и этот Bundle был установлен в качестве аргументов фрагмента. Затем был создан фрагмент StudentFragment, который в методе onViewCreated с помощью requireArguments() извлекал переданный номер и отображал его в текстовом поле TextView.

<img width="1592" height="909" alt="image" src="https://github.com/user-attachments/assets/1245abe4-2a0d-4c68-a81f-fffe04af16aa" />

Далее в новом модуле FragmentManagerApp была создана модель данных Country, содержащая информацию о названии страны, столице, населении и других характеристиках. Первоначально оба фрагмента были созданы как независимые компоненты - CountryListFragment со списком стран в ListView и CountryDetailsFragment с макетом для отображения детальной информации. На начальном этапе фрагменты не были связаны между собой, и при выборе страны в списке ничего не происходило. Затем для организации взаимодействия между фрагментами был создан SharedViewModel, который использовал LiveData для отслеживания выбранной страны. В CountryListFragment был добавлен обработчик onItemClickListener, который при выборе элемента передавал данные в SharedViewModel через метод selectCountry(). В CountryDetailsFragment был реализован Observer, который отслеживал изменения в SharedViewModel и автоматически обновлял интерфейс при получении новых данных. Оба фрагмента были размещены в activity_main.xml с помощью тегов fragment для организации многокомпонентного интерфейса и поэтапной реализации взаимодействия между фрагментами через общую ViewModel. 

<img width="1398" height="901" alt="image" src="https://github.com/user-attachments/assets/e8cd5a5a-5f79-4cf8-88f8-26d9838bce72" />
<img width="1623" height="907" alt="image" src="https://github.com/user-attachments/assets/08da64e2-4942-4c3e-9c03-1ecd0643638e" />

Следующим шагом был создан модуль ResultApiFragmentApp для передачи данных через Fragment Result API. Сначала был разработан DataFragment с полем ввода EditText и кнопкой отправки данных. Изначально кнопка не выполняла никаких действий, затем была реализована логика упаковки данных в Bundle и их передачи через setFragmentResult(). После этого был создан BottomSheetFragment, наследующийся от BottomSheetDialogFragment, который первоначально отображался как пустая bottom sheet панель. На следующем этапе в BottomSheetFragment был добавлен setFragmentResultListener(), который автоматически получал данные и отображал их в интерфейсе. Main Activity динамически добавляла DataFragment при запуске приложения, а BottomSheetFragment отображался поверх основного контента в виде выдвижной панели только после нажатия кнопки отправки.

<img width="1503" height="907" alt="image" src="https://github.com/user-attachments/assets/bf7d5214-700c-40b2-a3b9-9fe3c261c50b" />
<img width="1515" height="911" alt="image" src="https://github.com/user-attachments/assets/7906f781-578b-4b83-b76d-c21996def64d" />

Далее была проведена интеграция фрагментов в ранее созданное приложение DogCare. Вместо работы только с активностями, основная функциональность была разделена на отдельные фрагменты, что позволило создать модульную архитектуру. MainActivity теперь выступает в роли контейнера для фрагментов, обеспечивая их правильное отображение и управление жизненным циклом.

<img width="1136" height="847" alt="image" src="https://github.com/user-attachments/assets/3f9516ca-64cb-4f4a-a72c-0cf37292040f" />

Была настроена навигация между различными экранами приложения через систему фрагментов. Созданы методы для перехода к фрагменту профиля и фрагменту деталей породы, которые заменяют содержимое контейнера на соответствующий фрагмент. Навигация реализована через FragmentManager, это обеспечивает плавные переходы между экранами.

<img width="917" height="862" alt="image" src="https://github.com/user-attachments/assets/be8cc03a-9f44-43fa-9bd6-3a7d239c7196" />

Также была реализована поддержка бэк-стека при навигации между фрагментами. При смене фрагментов используется addToBackStack(), чтобы кнопка "Назад" возвращала на предыдущие экраны. Была переопределена логика обработки данной кнопки для корректного управления историей просмотров.

<img width="973" height="919" alt="image" src="https://github.com/user-attachments/assets/684c2ac7-c91a-4250-8113-cc6eb2e01239" />

Было выполнено разделение функционала приложения на отдельные фрагменты. Создан ProfileFragment для отображения информации о пользователе, BreedDetailsFragment для показа детальной информации о породах собак и CareAdviceFragment с советами по уходу. Каждый фрагмент отвечает за свою часть интерфейса и имеет собственную логику отображения данных.

<img width="1372" height="887" alt="image" src="https://github.com/user-attachments/assets/e002fcff-6ef7-4e82-99f1-5486e474f556" />

Были добавлены необходимые зависимости в файлы build.gradle для поддержки работы с фрагментами. Включены библиотеки AndroidX Fragment, которые обеспечивают современный и стабильный API для создания и управления фрагментами.

Даллее была адаптирована существующая система аутентификации для корректной работы с фрагментами. Настроена передача данных пользователя между активностями и фрагментами, что позволяет отображать информацию о текущем пользователе в профиле. Данные авторизации сохраняются при переходах между всеми экранами приложения.

<img width="1089" height="863" alt="image" src="https://github.com/user-attachments/assets/d93b1aac-5e76-4d25-8ecf-8cc916d46001" />

Результат работы приложения представлен ниже:

<img width="377" height="784" alt="image" src="https://github.com/user-attachments/assets/4a7738cc-6a58-47ba-bc7a-64b3fbb5c698" />
<img width="393" height="781" alt="image" src="https://github.com/user-attachments/assets/2379519f-f800-4b1d-a99e-acab7a84fb9e" />
<img width="384" height="777" alt="image" src="https://github.com/user-attachments/assets/e3efdad1-7f1f-4a69-a65c-3e058bde061a" />

<img width="361" height="768" alt="image" src="https://github.com/user-attachments/assets/ffddf42f-fe2a-498b-b9e7-23b6a7453eea" />
<img width="374" height="781" alt="image" src="https://github.com/user-attachments/assets/75605d79-ebef-405f-bb15-00dd562bed32" />
<img width="383" height="779" alt="image" src="https://github.com/user-attachments/assets/38255465-e9e0-4f66-b70a-122ef2346942" />

