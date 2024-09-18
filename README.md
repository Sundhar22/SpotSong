# SpotSong

SpotSong is a Java-based application that allows users to download songs from Spotify playlists using the Spotify API and YouTube. It integrates with the Spotify API to retrieve playlist details and uses a YouTube downloader to fetch and save audio tracks locally. Built with Maven, this project simplifies the process of creating a personal music library from Spotify playlists.

## Features

- Fetch Spotify playlists using the Spotify API.
- Download songs from YouTube based on the retrieved playlist data.
- Simple and efficient Java-based implementation.

## Requirements

- Java 8 or higher
- Maven
- Spotify Developer Account for API access
- YouTube downloader library

## Setup and Installation

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/Sundhar22/SpotSong.git
    ```

2. **Spotify API Setup**:
    - Create a Spotify Developer account.
    - Register your application and get the Client ID and Client Secret.
    - Set up the Redirect URI for authentication.

3. **Configure the Application**:
    - Add your Spotify API credentials to `application.properties` or as environment variables:
    ```properties
    spotify.client.id=YOUR_CLIENT_ID
    spotify.client.secret=YOUR_CLIENT_SECRET
    ```

4. **Build the Project**:
    ```bash
    mvn clean install
    ```

5. **Run the Application**:
    ```bash
    mvn exec:java
    ```

## Usage

- **Login**: Authenticate with your Spotify account to allow the application to access your playlists.
- **Select Playlist**: Choose a Spotify playlist to download songs from.
- **Download Songs**: The application fetches songs from YouTube based on the playlist data and downloads them.

## Dependencies

- [Spotify Web API](https://developer.spotify.com/documentation/web-api/)
- [YouTube Downloader](https://github.com/yt-dlp/yt-dlp)
- [Maven](https://maven.apache.org/)

## License

This project is licensed under the MIT License.

## Disclaimer

This tool is intended for personal use only. Downloading copyrighted material without permission is illegal. Ensure that you have the rights to download and use the content you are accessing.
