# Stepik-Project-Notepad

Учебный проект для управления заметками: просмотр заметок, просмотр заметки, создание/редактирование 
заметки. Хранение заметок в локальной БД.

## Требования

- **Android Studio** Meerkat (2025.1) или новее
- **JDK 11+**
- **Android SDK 36** (установится автоматически через SDK Manager)
- Устройство или эмулятор с **Android 9 (API 28)** или выше

## Сборка и запуск

### Вариант 1 — Готовый APK

Готовый APK находится в `app/release/app-release.apk`. Для установки:

```bash
adb install app/release/app-release.apk
```

### Вариант 2 — Сборка из исходников

1. Склонировать репозиторий и открыть папку `android/` в Android Studio.
2. Дождаться завершения Gradle Sync.
3. Выбрать конфигурацию **app** и нажать **Run**.

Сборка release-APK через терминал:

```bash
./gradlew assembleRelease
```

Собранный APK появится в `app/build/outputs/apk/release/`.

## Запуск тестов

```bash
# Unit-тесты и интеграционные тесты
./gradlew test

# UI-тесты (требуется подключённое устройство или эмулятор)
./gradlew connectedAndroidTest
```

## Структура проекта

```
android/
├── app/                  # Основной модуль приложения
│   └── src/
│       ├── main/         # Исходный код приложения
│       ├── test/         # Unit и интеграционные тесты
│       └── androidTest/  # UI-тесты
```