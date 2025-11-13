package ru.mirea.kuzmina.recyclerviewapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<HistoricalEvent> events = getListData();
        RecyclerView recyclerView = this.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new EventRecyclerViewAdapter(events));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    private List<HistoricalEvent> getListData() {
        List<HistoricalEvent> list = new ArrayList<HistoricalEvent>();

        list.add(new HistoricalEvent("Вторая мировая война",
                "Крупнейший вооружённый конфликт в истории человечества",
                "vtormir", 1939));

        list.add(new HistoricalEvent("Первый полёт в космос",
                "Юрий Гагарин стал первым человеком в космосе",
                "poletvcosmos", 1961));

        list.add(new HistoricalEvent("Падение Берлинской стены",
                "Объединение Восточной и Западной Германии",
                "padenieberlinsteny", 1989));

        list.add(new HistoricalEvent("Распад СССР",
                "Распад Советского Союза и образование СНГ",
                "raspad", 1991));

        list.add(new HistoricalEvent("Изобретение Интернета",
                "Создание Всемирной паутины Тимом Бернерсом-Ли",
                "internet", 1989));

        list.add(new HistoricalEvent("Французская революция",
                "Крупнейшая трансформация социальной и политической системы Франции",
                "franzrevol", 1789));

        list.add(new HistoricalEvent("Открытие Америки",
                "Христофор Колумб достиг берегов Америки",
                "openamerica", 1492));

        list.add(new HistoricalEvent("Холодная война",
                "Глобальное геополитическое противостояние",
                "holodvoina", 1947));

        list.add(new HistoricalEvent("Первая мировая война",
                "Один из самых широкомасштабных вооружённых конфликтов",
                "pervvoina", 1914));

        return list;
    }
}