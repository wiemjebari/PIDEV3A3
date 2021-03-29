-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 29 mars 2021 à 22:19
-- Version du serveur :  10.4.18-MariaDB
-- Version de PHP : 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projet`
--

-- --------------------------------------------------------

--
-- Structure de la table `livre_audio`
--

CREATE TABLE `livre_audio` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `editeur` varchar(255) NOT NULL,
  `id_categorie` int(11) NOT NULL,
  `duree` int(11) NOT NULL,
  `source` varchar(255) NOT NULL,
  `audio` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livre_audio`
--

INSERT INTO `livre_audio` (`id`, `titre`, `editeur`, `id_categorie`, `duree`, `source`, `audio`, `image`) VALUES
(1, 'fdk', 'fdlk', 4, 11, 'dsl', 'file:/C:/audio/Ludovic_Halevy_-_Noiraud.mp3', 'file:/C:/Users/walid/Documents/NetBeansProjects/LivreCRUD/src/image/bg.jpg');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `livre_audio`
--
ALTER TABLE `livre_audio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_categorie` (`id_categorie`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `livre_audio`
--
ALTER TABLE `livre_audio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
