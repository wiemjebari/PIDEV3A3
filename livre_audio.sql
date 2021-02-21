-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : Dim 21 fév. 2021 à 15:58
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 7.4.13

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
  `id` varchar(20) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `editeur` varchar(255) NOT NULL,
  `genre` varchar(255) NOT NULL,
  `durée` int(10) NOT NULL,
  `source` varchar(255) NOT NULL,
  `audio` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `livre_audio`
--
ALTER TABLE `livre_audio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `genre` (`genre`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `livre_audio`
--
ALTER TABLE `livre_audio`
  ADD CONSTRAINT `livre_audio_ibfk_1` FOREIGN KEY (`genre`) REFERENCES `categorie_audio` (`nom`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
