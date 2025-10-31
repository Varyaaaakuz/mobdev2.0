Для начал бы создан класс MainViewModel, который наследуется от ViewModel. В этом классе был добавлен конструктор с параметром MovieRepository, чтобы ViewModel не зависела от создания других объектов. Также был переопределен метод onCleared() для очистки ресурсов и добавиллено логирование, чтобы видеть когда ViewModel создается и удаляется. Был создан MutableLiveData для хранения данных о фильме.

<img width="1082" height="957" alt="image" src="https://github.com/user-attachments/assets/c8842338-60f8-488e-85eb-78d5109a6df4" />

Далее был создан специальный класс ViewModelFactory, который отвечает за создание ViewModel. Эта фабрика знает как правильно создать все зависимости: сначала хранилище, потом репозиторий, и только потом ViewModel. Такой подход позволяет ViewModel оставаться чистой и не зависеть от Android-компонентов.

<img width="999" height="888" alt="image" src="https://github.com/user-attachments/assets/725ca3ea-f4d7-4455-9284-1efe6b7e3b00" />

В MainActivity было настроено правильное создание ViewModel через ViewModelProvider с фабрикой. Также добавлено Observer для LiveData, который автоматически обновляет текст на экране когда данные меняются. Было добавлено логирование чтобы видеть когда Activity создается и удаляется.

<img width="1310" height="899" alt="image" src="https://github.com/user-attachments/assets/71c93647-8ae1-4f27-9417-10ff35bfffa7" />

Приложение на эмуляторе было запущено и работает корректно. Можно ввести название фильма, нажать "Сохранить", а потом нажать "Отобразить" и увидеть сохраненный фильм. Результат представлен ниже.

<img width="701" height="901" alt="image" src="https://github.com/user-attachments/assets/5ab9cd7d-adbd-4aeb-974c-67325e2f0d00" />
<img width="707" height="902" alt="image" src="https://github.com/user-attachments/assets/cf6ca574-e657-41ba-8314-f58a03cfb68e" />

При повороте экрана Activity пересоздается, но ViewModel остается той же самой. Это видно по логам - ViewModel создается только один раз при первом запуске, а при поворотах экрана создается только новая Activity.

<img width="1403" height="321" alt="image" src="https://github.com/user-attachments/assets/19df1df8-8b65-42e4-8b30-88b58d2b1e12" />

В контрольном задании было организовано взаимодействие между слоями приложения согласно принципам чистой архитектуры. MainActivity обращается к данным только через MainViewModel, который использует GetBreedsUseCase для работы с domain-слоем. Это обеспечивает четкое разделение ответственности между компонентами.

<img width="1153" height="804" alt="image" src="https://github.com/user-attachments/assets/4b055ce3-2083-4e71-97a1-6a0ccf2cf483" />
<img width="1000" height="819" alt="image" src="https://github.com/user-attachments/assets/f350f539-5440-4768-9910-d61bf9e4a127" />

Для быстрого обновления интерфейса был использован LiveData. В ViewModel созданы LiveData-поля, за которыми Activity наблюдает через Observer. При изменении данных в ViewModel автоматически обновляется RecyclerView, что делает приложение отзывчивым.
<img width="1106" height="557" alt="image" src="https://github.com/user-attachments/assets/3a010db5-3094-4d68-8272-7bf6404db0cf" />
<img width="1039" height="820" alt="image" src="https://github.com/user-attachments/assets/39f242c4-8f7b-4d49-817a-e0528cafffb4" />

Далее был изучен MediatorLiveData и применен для объединения данных из разных источников. MediatorLiveData отслеживает изменения в двух LiveData-источниках и комбинирует их по заданной логике. Это позволяет реализовать стратегию загрузки данных.

<img width="811" height="793" alt="image" src="https://github.com/user-attachments/assets/d3bc0294-4413-4c58-a829-97d05476a2e5" />

Для демонстрации работы с разными источниками данных были созданы моки в GetBreedsUseCase. Локальные данные имитируют информацию из базы данных, а сетевые данные - свежую информацию из API. Каждый источник возвращает разные наборы пород собак.

<img width="1765" height="714" alt="image" src="https://github.com/user-attachments/assets/71c4b390-6d84-48e9-8dc1-11208f9303d3" />

Была разработана логика объединения данных из разных источников. При загрузке сначала показываются данные из БД, а через несколько секунд добавляются данные из сети. Если порода присутствует в обоих источниках, приоритет отдается сетевым данным.

<img width="896" height="821" alt="image" src="https://github.com/user-attachments/assets/f786262a-0a1e-4247-80f7-3c88bfa7b380" />

Для имитации реальных условий были добавлены задержки при загрузке данных. Данные из БД загружаются через 1 секунду, а из сети - через 5 секунд. Это демонстрирует, как приложение работает с источниками разной скорости отклика. Были также добавлены визуальные подсказки для пользователя. Пользователь видит, когда загружаются данные из кэша и когда обновляются из сети. Результат представлен ниже.
<img width="342" height="712" alt="image" src="https://github.com/user-attachments/assets/c775dd83-c9bd-45d8-bb7b-f5d2577e105e" />
<img width="349" height="716" alt="image" src="https://github.com/user-attachments/assets/886020d3-69fd-489d-89f2-e239d30b278c" />

 Приложение демонстрирует правильную архитектуру с использованием ViewModel для доступа к domain-слою, LiveData для обновления UI и MediatorLiveData для комбинирования данных из разных источников. Пользователь видит поэтапную загрузку данных и может взаимодействовать с приложением через поиск и обновление.
