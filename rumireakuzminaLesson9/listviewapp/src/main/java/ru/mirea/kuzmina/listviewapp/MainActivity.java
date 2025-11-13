package ru.mirea.kuzmina.listviewapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Book> books = new ArrayList<>();
        books.add(new Book("Фёдор Достоевский", "Братья Карамазовы"));
        books.add(new Book("Лев Толстой", "Война и мир"));
        books.add(new Book("Михаил Булгаков", "Мастер и Маргарита"));
        books.add(new Book("Александр Пушкин", "Евгений Онегин"));
        books.add(new Book("Николай Гоголь", "Мёртвые души"));
        books.add(new Book("Антон Чехов", "Рассказы"));
        books.add(new Book("Иван Тургенев", "Отцы и дети"));
        books.add(new Book("Александр Солженицын", "Архипелаг ГУЛАГ"));
        books.add(new Book("Владимир Набоков", "Лолита"));
        books.add(new Book("Михаил Лермонтов", "Герой нашего времени"));
        books.add(new Book("Иван Бунин", "Тёмные аллеи"));
        books.add(new Book("Алексей Толстой", "Пётр Первый"));
        books.add(new Book("Михаил Шолохов", "Тихий Дон"));
        books.add(new Book("Борис Пастернак", "Доктор Живаго"));
        books.add(new Book("Александр Грибоедов", "Горе от ума"));
        books.add(new Book("Джордж Оруэлл", "1984"));
        books.add(new Book("Олдос Хаксли", "О дивный новый мир"));
        books.add(new Book("Рэй Брэдбери", "451 градус по Фаренгейту"));
        books.add(new Book("Фрэнсис Скотт Фицджеральд", "Великий Гэтсби"));
        books.add(new Book("Эрнест Хемингуэй", "Старик и море"));
        books.add(new Book("Габриэль Гарсиа Маркес", "Сто лет одиночества"));
        books.add(new Book("Харпер Ли", "Убить пересмешника"));
        books.add(new Book("Джером Сэлинджер", "Над пропастью во ржи"));
        books.add(new Book("Джон Толкин", "Властелин колец"));
        books.add(new Book("Джоан Роулинг", "Гарри Поттер"));
        books.add(new Book("Стивен Кинг", "Зелёная миля"));
        books.add(new Book("Агата Кристи", "Десять негритят"));
        books.add(new Book("Артур Конан Дойл", "Шерлок Холмс"));
        books.add(new Book("Дэн Браун", "Код да Винчи"));
        books.add(new Book("Пауло Коэльо", "Алхимик"));
        books.add(new Book("Харуки Мураками", "Норвежский лес"));
        books.add(new Book("Виктор Гюго", "Отверженные"));
        books.add(new Book("Стендаль", "Красное и чёрное"));
        books.add(new Book("Оноре де Бальзак", "Человеческая комедия"));
        books.add(new Book("Гюстав Флобер", "Госпожа Бовари"));
        books.add(new Book("Марсель Пруст", "В поисках утраченного времени"));
        books.add(new Book("Джейн Остин", "Гордость и предубеждение"));
        books.add(new Book("Шарлотта Бронте", "Джейн Эйр"));
        books.add(new Book("Эмили Бронте", "Грозовой перевал"));
        books.add(new Book("Чарльз Диккенс", "Большие надежды"));

        ListView booksListView = findViewById(R.id.books_list_view);
        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                books) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);
                Book book = getItem(position);
                text1.setText(book.getTitle());
                text2.setText(book.getAuthor());
                return view;
            }
        };

        booksListView.setAdapter(adapter);
    }
}