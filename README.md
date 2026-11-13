# AppSleeper

App Android in Kotlin e Jetpack Compose. La versione iniziale mostra «AppSleeper» al centro dello schermo. Le funzionalità previste sono descritte nel [documento di architettura](doc/AppSleeper_architettura.md).

## Requisiti

- JDK 25 con vendor Oracle, come la distribuzione OpenJDK di Oracle
- Android SDK Platform 37.0 (Android 17) e Build Tools compatibili con AGP 9.5.1
- Android Studio Rabbit 2 o una versione che supporti AGP 9.5.1

Il progetto usa il wrapper Gradle 9.9.1 incluso nel repository. Configura il percorso dell'Android SDK tramite `ANDROID_HOME` o un file `local.properties` con `sdk.dir`.

I requisiti Java del daemon sono definiti in `gradle/gradle-daemon-jvm.properties`, da conservare nel repository. Gradle usa questi criteri sia dall'IDE sia dal terminale: cerca un JDK compatibile installato e, se manca, può scaricare OpenJDK dagli URL configurati tramite Foojay. Il vendor `ORACLE` identifica anche le build OpenJDK di Oracle.

## Compilazione

Su Windows:

```powershell
.\gradlew.bat :app:assembleDebug
```

Su macOS e Linux:

```sh
./gradlew :app:assembleDebug
```

L'APK viene creato in `app/build/outputs/apk/debug/`.

## Test di avvio

Crea e avvia un dispositivo virtuale con immagine Android 17 / API 37.0, oppure collega un dispositivo con Android 17. Il test strumentale apre l'Activity e verifica che il titolo sia visibile tramite UI Automator.

```powershell
.\gradlew.bat :app:connectedDebugAndroidTest
```

Su macOS e Linux usa `./gradlew` al posto di `.\gradlew.bat`. Esegui il test dopo che il dispositivo o l'emulatore ha completato l'avvio.

## Lingue

L'inglese è la lingua predefinita in `app/src/main/res/values/strings.xml`; l'italiano è in `app/src/main/res/values-it/strings.xml`. Gradle rileva le cartelle `values-*` dell'app e genera automaticamente l'elenco delle lingue disponibili nelle impostazioni Android, escludendo le traduzioni presenti solo nelle dipendenze.

Per aggiungere una lingua, crea una cartella `values-<codice ISO a due lettere>` o `values-b+<tag BCP 47>` sotto `app/src/main/res/` e aggiungi le stringhe tradotte. L'elenco delle lingue si aggiorna alla build successiva.
