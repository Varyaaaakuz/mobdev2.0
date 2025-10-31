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


<img width="342" height="712" alt="image" src="https://github.com/user-attachments/assets/c775dd83-c9bd-45d8-bb7b-f5d2577e105e" />
<img width="349" height="716" alt="image" src="https://github.com/user-attachments/assets/886020d3-69fd-489d-89f2-e239d30b278c" />

