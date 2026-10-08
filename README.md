# AtlasVision TV

Projet Android TV compatible Android 5.0+ (API 21). Il contient une interface télécommande, un lecteur plein écran et une sélection limitée de sources officielles gratuites : France 24 Français/English/Arabic (HLS) et Al Jazeera Arabic (ouverture du direct YouTube officiel).

## GitHub

1. Décompresse l’archive.
2. Crée un dépôt GitHub vide.
3. Glisse tout le contenu du dossier `AtlasVisionTV` dans GitHub, y compris `.github`.
4. Fais **Commit changes**.
5. Va dans **Actions** et ouvre `Build AtlasVision TV APK`.
6. Après succès, télécharge `AtlasVisionTV-debug-apk` dans **Artifacts**.

## Chaînes

Le fichier `app/src/main/assets/channels.json` contient les chaînes. Ajoute uniquement des sources dont la diffusion est expressément autorisée. Les URL et disponibilités peuvent changer selon le pays ou le diffuseur.
