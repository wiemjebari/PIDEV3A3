-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 29 mars 2021 à 20:10
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 7.4.14

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
-- Structure de la table `centre`
--

CREATE TABLE `centre` (
  `id` int(20) NOT NULL,
  `nom_centre` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `services` varchar(255) NOT NULL,
  `num_tel` int(11) NOT NULL,
  `e_mail` varchar(255) NOT NULL
) ;

--
-- Déchargement des données de la table `centre`
--

INSERT INTO `centre` (`id`, `nom_centre`, `adresse`, `services`, `num_tel`, `e_mail`) VALUES
(30, 'Beauty', '56rue jasmin', 'spa', 71560317, 'beauty@tunis.tn'),
(32, 'RELAX', '13 Rue de la h', 'massage', 71560317, 'relax@yahoo.fr'),
(43, 'HELENA', '45rue des jj', 'massage', 71560317, 'helena@gmail.com'),
(46, 'QUEEN', '59 rue des jonjon', 'spa', 71560317, 'queen@gmail.com'),
(47, 'BELLA', '58 rue des belles', 'SPA', 71560317, 'bellal@gmail.com'),
(48, 'JOELLE', '14 rue des yeux ', 'massage', 71560317, 'Joelle@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `reservation_centre`
--

CREATE TABLE `reservation_centre` (
  `id_reservation` int(20) NOT NULL,
  `nom_centre` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `services` varchar(255) NOT NULL,
  `prix_service` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation_centre`
--

INSERT INTO `reservation_centre` (`id_reservation`, `nom_centre`, `date`, `services`, `prix_service`) VALUES
(8, 'RELAX', '2021-03-14', 'massage', 45),
(9, 'QUEEN', '2021-03-31', 'spa', 40),
(10, 'MARHABA', '2021-03-18', 'spa', 40),
(13, 'HELENA', '2021-03-26', 'SPA', 45),
(15, 'Beauty', '2021-03-26', 'massage', 40);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `centre`
--
ALTER TABLE `centre`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reservation_centre`
--
ALTER TABLE `reservation_centre`
  ADD PRIMARY KEY (`id_reservation`),
  ADD KEY `id` (`nom_centre`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `centre`
--
ALTER TABLE `centre`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservation_centre`
--
ALTER TABLE `reservation_centre`
  MODIFY `id_reservation` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
