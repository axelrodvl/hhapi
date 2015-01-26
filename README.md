# hhapi
HeadHunter.ru API Testing

API documentation:
- https://github.com/hhru/api/blob/master/docs/general.md
- https://github.com/hhru/api/blob/master/docs/authorization.md
- https://github.com/hhru/api/blob/master/docs/me.md

Automated tests for API.me method and OAuth authorization tools

Using:
- Maven: build automation tool
- JUnit: test creation
- Apache Oltu: OAuth requests generation
- Selenium: working with websites
- Google Simple JSON: JSON handling

Tests list:
```
1) TestGetPositive
- getInfo
2) TestGetNegative
- incorrectAccessToken
3) TestPostPositive
- setNameFull
- setNameFullPunctuationMark
- setNameFirstAndLast
- setIsInSearchTrue
- setIsInSearchFalse
- setIsInSearchIncorrectString
- setIsInSearchIncorrectStringNumbers
4) TestPostNegative
- setNameFirstNamePunctuationMarks
- setNameLastNamePunctuationMarks
- setNameMiddleNamePunctuationMarks
- setNameFirstNameNumbers
- setNameLastNameNumbers
- setNameMiddleNameNumbers
- setNameEmptyFullName
- setNameEmptyFirstName
- setNameEmptyLastName
- setNameOnlyLastName
- setNameOnlyFirstName
- setNameOnlyMiddleName
- setNameOnlyLastAndMiddleName
- setNameOnlyFirstAndMiddleName
- setNameAndIsInSearch
- incorrectAccessToken
```

Tests description (in Russian):
```
1. Получение информации
  1.1 Положительные тесты
    1.1.1 Получение информации о пользователе (соискателе)
  1.2 Отрицательные тесты
    1.2.1 Попытка доступа с некорректным accessToken
2. Редактирование информации
  2.1 Положительные тесты
    2.1.1 Редактирование фамилии, имени и отчества
    2.1.2 Редактирование фамилии, имени и отчества (значения со знаком "-")
    2.1.3 Редактирование фамилии и имени (отчество пустое)
    2.1.4 Редактирование флага "ишу/не ищу работу" = true
    2.1.5 Редактирование флага "ишу/не ищу работу" = false
    2.1.6* Передача некорректного строкового значения во флаг "ищу/не ищу работу"
    2.1.7* Передача численного значения во флаг "ищу/не ищу работу"
  2.2 Отрицательные тесты
    2.2.1** Некорректное значение имени (знаки кроме "-")
    2.2.2** Некорректное значение фамилии (знаки кроме "-")
    2.2.3** Некорректное значение отчества (знаки кроме "-")
    2.2.4** Некорректное значение имени (числа)
    2.2.5** Некорректное значение фамилии (числа)
    2.2.6** Некорректное значение отчества (числа)
    2.2.7 Пустое ФИО
    2.2.8 Пустое имя
    2.2.9 Пустая фамилия
    2.2.10 Только фамилия
    2.2.11 Только имя
    2.2.12 Только отчество
    2.2.13 Только фамилия и отчество
    2.2.14 Только имя и отчество
    2.2.15 Редактирование имени вместе с флагом "ищу/не ищу работу"
    2.2.16 Попытка доступа с некорректным accessToken
```
*В документации не указано поведение API при передаче некорректного значения флага is_in_search (ищу/не ищу работу) - поэтому данное поведение отнесено к положительным тестам из предположения: любые значения is_in_search, кроме "true", являются идентичными "false".

**В документации не указано поведение API при передаче некорректных значений first_name, last_name и middle_name. Тем не менее, при редактировании этих полей в веб-интерфейсе на странице http://hh.ru/applicant/settings выдается предупреждение "Только буквы и дефис", поэтому данное поведение отнесено к отрицательным тестам, хотя может также являться корректным, в зависимости от требований к проверке передаваемых значений у клиента API. 
