Для начала был создан новый модуль ScrollViewApp через меню File → New → New Module. В файле разметки activity_main.xml был размещен контейнер ScrollView с внутренним LinearLayout, который выступает в качестве обертки для динамически добавляемых элементов. С помощью LayoutInflater в цикле for было создано 100 представлений элементов, где каждый TextView отображал элемент геометрической прогрессии с знаменателем 2, рассчитанный с использованием класса BigDecimal для работы с большими числами. Для отображения данных был реализован механизм программного управления прокруткой через методы scrollTo() и scrollBy().

<img width="1683" height="896" alt="image" src="https://github.com/user-attachments/assets/9fb638c9-df8d-44b3-b48d-a178b10884a7" />
.

Далее был создан модуль ListViewApp, в разметке которого размещен компонент ListView с идентификатором country_list_view. Для связи данных с элементами списка использован ArrayAdapter с переопределением метода getView(), что позволяет отображать два текстовых поля в каждом элементе списка по аналогии с simple_list_item_2. Были созданы массивы данных, содержащие более 30 элементов - авторов и названий книг. Реализован паттерн ViewHolder для оптимизации производительности при прокрутке списка.

<img width="1498" height="907" alt="image" src="https://github.com/user-attachments/assets/3c150c50-1233-4dc6-8d1e-11b0de73c5c5" />

Также был создан модуль RecyclerViewApp с использованием современного компонента RecyclerView, который пришел на смену ListView и GridView. Реализована трехкомпонентная архитектура: Adapter - наследник RecyclerView.Adapter, ViewHolder - наследник RecyclerView.ViewHolder и LayoutManager (LinearLayoutManager). В адаптере переопределены три обязательных метода: onCreateViewHolder() для создания новых представлений, onBindViewHolder() для привязки данных и getItemCount() для возврата количества элементов. Для отображения исторических событий создана модель данных HistoricalEvent, содержащая название события, описание, год и имя изображения.

<img width="1366" height="922" alt="image" src="https://github.com/user-attachments/assets/29439071-3680-42c4-883b-365724b8ba77" />
