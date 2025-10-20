<img width="1712" height="863" alt="image" src="https://github.com/user-attachments/assets/dce2b2f6-0b25-40cf-b33a-fd4e7fb10fda" />
<img width="563" height="765" alt="image" src="https://github.com/user-attachments/assets/4a8c373e-47b8-40d9-a356-e5ff57a69621" />

<img width="1068" height="759" alt="image" src="https://github.com/user-attachments/assets/a6945d39-bfe4-4021-842c-8da03a828c4e" />

<img width="1060" height="775" alt="image" src="https://github.com/user-attachments/assets/ee7eae1a-44de-494b-810f-7bef267f20fc" />

<img width="437" height="857" alt="image" src="https://github.com/user-attachments/assets/18890f87-9c3c-4fb9-b176-af2f0f299d18" />
<img width="427" height="842" alt="image" src="https://github.com/user-attachments/assets/efefc62b-981f-4def-8ce4-4dbd4a8ce1f9" />

Был создан детальный прототип приложения в figma, включающий основные экраны: авторизацию, каталог пород, определение породы по фото и рекомендации по уходу. Прототип отражает пользовательские сценарии и визуальный дизайн приложения, что позволило четко спланировать разработку. Результат представлен ниже.

<img width="339" height="727" alt="image" src="https://github.com/user-attachments/assets/c0bcad70-54f6-40d4-8d40-9d3d13b3fedf" />
<img width="333" height="746" alt="image" src="https://github.com/user-attachments/assets/4484fa3f-f20d-48d9-a378-f1a7f4f977cc" />
<img width="344" height="747" alt="image" src="https://github.com/user-attachments/assets/5a5607eb-f51d-42e2-8936-9a242ef872bf" />
<img width="340" height="750" alt="image" src="https://github.com/user-attachments/assets/d738116e-2d1c-467b-affb-f5c8b75b546c" />
<img width="337" height="741" alt="image" src="https://github.com/user-attachments/assets/aaeb2111-9afe-4edc-ab44-eecca48cb89b" />
<img width="334" height="750" alt="image" src="https://github.com/user-attachments/assets/b45a4761-bf4f-433d-aeea-3d0738f23f6c" />

Были созданы отдельные модули data и domain для разделения ответственности между слоями приложения, соответствующий
код приложения был перенесён в данные модули. Модуль domain содержит бизнес-логику и интерфейсы, а модуль data - реализации репозиториев и работу с данными.

<img width="678" height="785" alt="image" src="https://github.com/user-attachments/assets/efa109e0-9c26-4d48-8eea-865eb43fe14a" />

Была создана LoginActivity в presentation слое, которая использует use cases из domain модуля для выполнения операций входа и регистрации. Use cases работают через AuthRepository интерфейс, реализация которого находится в data модуле и интегрируется с Firebase Authentication. Такое разделение обеспечивает полную независимость слоев и возможность легкой замены механизма аутентификации.
<img width="1498" height="913" alt="image" src="https://github.com/user-attachments/assets/3f40776a-ad45-41df-be9b-9eeefed70441" />
<img width="383" height="785" alt="image" src="https://github.com/user-attachments/assets/68fa7855-b7b5-42ba-ad50-80ad4209634d" /><img width="368" height="795" alt="image" src="https://github.com/user-attachments/assets/5ab6be3c-1647-44e4-843c-50fce9ddb654" />
<img width="1599" height="664" alt="image" src="https://github.com/user-attachments/assets/5f3d5d1a-2f5f-4815-8d7e-11b5282c1009" />

В AuthRepositoryImpl были интегрированы три различных способа работы с данными: SharedPreferences для хранения простых пользовательских настроек, Room для сохранения структурированных данных в локальной базе данных и NetworkApi для организации работы с внешним API. Каждый способ обрабатывает данные независимо, что обеспечивает надежность и отказоустойчивость приложения.
<img width="964" height="848" alt="image" src="https://github.com/user-attachments/assets/a947e5e1-021a-4922-95e2-c5d44c51dff3" />

Были созданы доменные модели User и DogBreed, которые содержат только данные без бизнес-логики. Use cases инкапсулируют конкретные бизнес-сценарии приложения, такие как вход пользователя, получение списка пород и идентификация породы по фото. Каждый use case работает исключительно с domain-интерфейсами, что обеспечивает полную независимость от конкретных реализаций в data слое.

<img width="1104" height="844" alt="image" src="https://github.com/user-attachments/assets/1e05379f-7c5d-40f2-97cb-3cef3b72825f" />

Были настроены градиентные зависимости между модулями через файлы build.gradle.kts. Модуль app зависит от data и domain, что позволяет presentation слою использовать все возможности приложения. Модуль data зависит только от domain, обеспечивая направление зависимостей от низкоуровневых к высокоуровневым слоям. Такая конфигурация предотвращает циклические зависимости и соответствует принципам чистой архитектуры.

Листинг build.gradle.kts для модуля app:

<img width="900" height="895" alt="image" src="https://github.com/user-attachments/assets/b9b3d9e9-ce3c-4ee0-9629-9bd3c4bdd34a" />

Листинг build.gradle.kts для модуля data:

<img width="807" height="855" alt="image" src="https://github.com/user-attachments/assets/2326dc62-b8ff-4354-b39a-ebebc7f01229" />

Листинг build.gradle.kts для модуля domain:

<img width="660" height="293" alt="image" src="https://github.com/user-attachments/assets/a63d99a8-e522-4d87-a32b-1ca0e77871f1" />

В ходе работы реализована авторизация через Firebase, три способа работы с данными и основной функционал приложения. Применение модульного подхода позволило создать поддерживаемую и тестируемую архитектуру, соответствующую современным стандартам разработки Android-приложений. Разделение на модули data, domain и app обеспечило четкое разделение ответственности и возможность независимого развития каждого компонента системы.


