-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 29 mars 2021 à 12:45
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 7.4.15

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
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `id` int(10) NOT NULL,
  `note` int(5) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_randonne` int(5) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `randonnees`
--

CREATE TABLE `randonnees` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `heure_depart` int(10) NOT NULL,
  `heure_retour` int(10) NOT NULL,
  `nbr_places` int(10) NOT NULL,
  `photo` varchar(255) NOT NULL,
  `prix` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `randonnees`
--

INSERT INTO `randonnees` (`id`, `date`, `lieu`, `heure_depart`, `heure_retour`, `nbr_places`, `photo`, `prix`) VALUES
(60, '2021-04-02', 'seliana', 6, 20, 30, 'C:\\Users\\ASUS\\Downloads\\siliana.jfif', 30),
(68, '2021-03-11', 'Sousse', 10, 21, 50, 'C:\\Users\\ASUS\\Downloads\\sousse.jpg', 15),
(69, '2021-04-02', 'Tatawin', 4, 21, 4, 'C:\\Users\\ASUS\\Downloads\\tatawin.jpg', 80),
(72, '2021-03-19', 'Kesra', 6, 18, 10, 'C:\\Users\\ASUS\\Downloads\\siliana.jfif', 60),
(73, '2021-03-17', 'Kairouan', 6, 18, 20, 'C:\\Users\\ASUS\\Downloads\\kairouan.jpg', 50),
(74, '2021-03-27', 'kesra', 4, 18, 12, 'C:\\Users\\ASUS\\Downloads\\siliana.jfif', 50),
(75, '2021-03-25', 'touzeur', 7, 23, 10, 'C:\\Users\\ASUS\\Downloads\\touzeur.jfif', 90);

-- --------------------------------------------------------

--
-- Structure de la table `reservationrandonnees`
--

CREATE TABLE `reservationrandonnees` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `etat` varchar(255) NOT NULL,
  `id_user` int(10) NOT NULL,
  `id_randonnee` int(10) NOT NULL,
  `nbr_places` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservationrandonnees`
--

INSERT INTO `reservationrandonnees` (`id`, `date`, `etat`, `id_user`, `id_randonnee`, `nbr_places`) VALUES
(19, '2021-03-12', 'CONFIRME', 15, 68, 6),
(21, '2021-03-12', 'CONFIRME', 15, 69, 2),
(22, '2021-03-12', 'CONFIRME', 15, 60, 2),
(24, '2021-03-12', 'REFUSE', 15, 68, 45),
(25, '2021-03-12', 'CONFIRME', 15, 68, 10),
(26, '2021-03-27', 'ENATTENTE', 15, 72, 3),
(29, '2021-03-27', 'ENATTENTE', 15, 72, 5),
(30, '2021-03-27', 'CONFIRME', 15, 75, 2);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `role` varchar(40) NOT NULL,
  `date_naissance` date NOT NULL,
  `login` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `email` date NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `telephone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `role`, `date_naissance`, `login`, `password`, `email`, `adresse`, `telephone`) VALUES
(15, 'ichraf', 'aaa', 'zz', '2021-03-01', 'ichraf', 'ichraf', '0000-00-00', 'ariana', 21345687);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `randonnees`
--
ALTER TABLE `randonnees`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reservationrandonnees`
--
ALTER TABLE `reservationrandonnees`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_randonnee` (`id_randonnee`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `avis`
--
ALTER TABLE `avis`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `randonnees`
--
ALTER TABLE `randonnees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT pour la table `reservationrandonnees`
--
ALTER TABLE `reservationrandonnees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `reservationrandonnees`
--
ALTER TABLE `reservationrandonnees`
  ADD CONSTRAINT `reservationrandonnees_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `reservationrandonnees_ibfk_2` FOREIGN KEY (`id_randonnee`) REFERENCES `randonnees` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
