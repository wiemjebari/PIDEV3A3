-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 29 mars 2021 à 11:46
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.0

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
-- Structure de la table `jeux`
--

CREATE TABLE `jeux` (
  `id` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `nom_cat` varchar(30) NOT NULL,
  `nom_jeu` varchar(30) NOT NULL,
  `niv_diff` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `jeux`
--

INSERT INTO `jeux` (`id`, `id_client`, `nom_cat`, `nom_jeu`, `niv_diff`) VALUES
(26, 21, 'Sports', 'Snake', 1),
(28, 23, 'Adventure', 'Green-Hill', 2),
(29, 3, 'Yoga', 'Ping-Pong', 3),
(30, 24, 'Adventure', 'Snake', 2);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id_client` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `sexe` varchar(30) NOT NULL,
  `date_naissance` date NOT NULL,
  `mail` varchar(30) NOT NULL,
  `adresse` varchar(30) NOT NULL,
  `role` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id_client`, `nom`, `prenom`, `sexe`, `date_naissance`, `mail`, `adresse`, `role`, `password`) VALUES
(3, 'fder', 'trgrg', 'Femme', '2021-03-10', 'fdgdf', 'gfhgh@', 'Admin', 'jhj01'),
(20, 'fsgfsgf', 'fdghfsh', 'Femme', '2021-03-10', 'dfdgdgf', 'ddsgfgfs@dfdf', 'Client', 'dsfdsgfs05'),
(21, 'zayet', 'hamed', 'Homme', '2022-03-24', 'poir', 'dfg@mail.com', 'Admin', 'dfdg01'),
(22, 'fdfd', 'ere', 'Femme', '2021-03-05', 'erere@oili', 'fdf', 'Client', 'sdds05'),
(23, 'zayet', 'fatma', 'Femme', '1992-03-17', 'italy05', 'fatma@gmail.com', 'Client', 'dfret05'),
(24, 'ichraf', 'tyu', 'Femme', '2021-03-17', 'mouroug', 'dfggh@fdf.com', 'Admin', 'eretrtr01');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `jeux`
--
ALTER TABLE `jeux`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_client` (`id_client`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_client`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `jeux`
--
ALTER TABLE `jeux`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id_client` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `jeux`
--
ALTER TABLE `jeux`
  ADD CONSTRAINT `jeux_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `user` (`id_client`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
