CREATE TABLE `users` (
  `id` int PRIMARY KEY NOT NULL,
  `userName` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(max),
  `name` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `createdAt` DateTime NOT NULL,
  `updatedAt` DateTime,
  `createdBy` int NOT NULL,
  `updatedBy` int
);

CREATE TABLE `roles` (
  `id` int PRIMARY KEY NOT NULL,
  `name` varchar(30) NOT NULL,
  `createdAt` DateTime NOT NULL,
  `updatedAt` DateTime,
  `createdBy` int NOT NULL,
  `updatedBy` int
);

CREATE TABLE `usersRoles` (
  `id` int PRIMARY KEY NOT NULL,
  `rolesId` int NOT NULL,
  `usersId` int NOT NULL,
  `createdAt` DateTime NOT NULL,
  `updatedAt` DateTime,
  `createdBy` int NOT NULL,
  `updatedBy` int
);

CREATE TABLE `buildings` (
  `id` int PRIMARY KEY NOT NULL,
  `name` varchar(20) NOT NULL,
  `createdAt` DateTime NOT NULL,
  `updatedAt` DateTime,
  `createdBy` int NOT NULL,
  `updatedBy` int
);

CREATE TABLE `appartments` (
  `id` int PRIMARY KEY NOT NULL,
  `buildingsId` int NOT NULL,
  `name` varchar(20) NOT NULL,
  `number` int NOT NULL,
  `ownerId` int NOT NULL,
  `createdAt` DateTime NOT NULL,
  `updatedAt` DateTime,
  `createdBy` int NOT NULL,
  `updatedBy` int
);

CREATE TABLE `maintenancePays` (
  `id` int PRIMARY KEY NOT NULL,
  `maintenanceHeadersId` int NOT NULL,
  `usersId` int NOT NULL,
  `createdAt` DateTime NOT NULL,
  `updatedAt` DateTime,
  `createdBy` int NOT NULL,
  `updatedBy` int
);

CREATE TABLE `maintenanceHeaders` (
  `id` int PRIMARY KEY NOT NULL,
  `totalCost` double,
  `createdAt` DateTime NOT NULL,
  `updatedAt` DateTime,
  `createdBy` int NOT NULL,
  `updatedBy` int
);

CREATE TABLE `maintenanceLines` (
  `id` int PRIMARY KEY NOT NULL,
  `maintenanceHeadersId` int NOT NULL,
  `description` varchar(50) NOT NULL,
  `cost` double,
  `createdAt` DateTime NOT NULL,
  `updatedAt` DateTime,
  `createdBy` int NOT NULL,
  `updatedBy` int
);

CREATE TABLE `visitors` (
  `id` int PRIMARY KEY NOT NULL,
  `date` DateTime NOT NULL,
  `name` varchar(30) NOT NULL,
  `ownerId` int NOT NULL,
  `appartmentsId` int NOT NULL
);

ALTER TABLE `usersRoles` ADD FOREIGN KEY (`usersId`) REFERENCES `users` (`id`);

ALTER TABLE `usersRoles` ADD FOREIGN KEY (`rolesId`) REFERENCES `roles` (`id`);

ALTER TABLE `usersRoles` ADD FOREIGN KEY (`createdBy`) REFERENCES `users` (`id`);

ALTER TABLE `usersRoles` ADD FOREIGN KEY (`updatedBy`) REFERENCES `users` (`id`);

ALTER TABLE `buildings` ADD FOREIGN KEY (`createdBy`) REFERENCES `users` (`id`);

ALTER TABLE `buildings` ADD FOREIGN KEY (`updatedBy`) REFERENCES `users` (`id`);

ALTER TABLE `appartments` ADD FOREIGN KEY (`buildingsId`) REFERENCES `buildings` (`id`);

ALTER TABLE `appartments` ADD FOREIGN KEY (`ownerId`) REFERENCES `users` (`id`);

ALTER TABLE `appartments` ADD FOREIGN KEY (`createdBy`) REFERENCES `users` (`id`);

ALTER TABLE `appartments` ADD FOREIGN KEY (`updatedBy`) REFERENCES `users` (`id`);

ALTER TABLE `maintenancePays` ADD FOREIGN KEY (`usersId`) REFERENCES `users` (`id`);

ALTER TABLE `maintenancePays` ADD FOREIGN KEY (`maintenanceHeadersId`) REFERENCES `maintenanceHeaders` (`id`);

ALTER TABLE `maintenanceLines` ADD FOREIGN KEY (`maintenanceHeadersId`) REFERENCES `maintenanceHeaders` (`id`);

ALTER TABLE `maintenanceLines` ADD FOREIGN KEY (`createdBy`) REFERENCES `users` (`id`);

ALTER TABLE `maintenanceLines` ADD FOREIGN KEY (`updatedBy`) REFERENCES `users` (`id`);

ALTER TABLE `visitors` ADD FOREIGN KEY (`appartmentsId`) REFERENCES `appartments` (`id`);

ALTER TABLE `visitors` ADD FOREIGN KEY (`ownerId`) REFERENCES `users` (`id`);
