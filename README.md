# WB_WEEK_8_1
Приложение получает список героев через API https://api.opendota.com/api/herostats
На экране ListHeroesFragment отображается список героев + иконка. 
По клику на героя открывается экран HeroDetailsFragment, где отображаются данные о выбранном героее.
При первом запуске приложения данные сохраняются в файл DataHeroes.txt. При повторном открытии приложения все данные загружаются из этого файла.
Используемый стек:
MVVM, Single Activity, LiveData, OKHTTP, Moshi, Coil.
