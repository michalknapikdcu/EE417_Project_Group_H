DROP TABLE IF EXISTS sensor;

CREATE TABLE sensor (
  id INT NOT NULL,
  name VARCHAR(45) DEFAULT NULL,
  unit VARCHAR(45) DEFAULT NULL,
  Location VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO sensor(id, name, unit, Location) VALUES
(1, 'Temperature', 'Celsius', 'Room 1'),
(2, 'Humidity', 'Percentage', 'Room 1'),
(3, 'Temperature', 'Celsius', 'Room 2'),
(4, 'Humidity', 'Percentage', 'Room 2'),
(5, 'Temperature', 'Celsius', 'Room 3'),    
(6, 'Humidity', 'Percentage', 'Room 3'),
(7, 'Temperature', 'Celsius', 'Room 4'),
(8, 'Humidity', 'Percentage', 'Room 4'),
(9, 'Temperature', 'Celsius', 'Room 5'),
(10, 'Humidity', 'Percentage', 'Room 5'),
(11, 'Noise', 'Decibel', 'Room 1'),
(12, 'Noise', 'Decibel', 'Room 2'),
(13, 'Noise', 'Decibel', 'Room 3'),
(14, 'Noise', 'Decibel', 'Room 4'),
(15, 'Noise', 'Decibel', 'Room 5'),
(16, 'Parking', 'Spaces', 'Parking Lot'),
(17, 'Occupancy', 'People', 'Room 1'),
(18, 'Occupancy', 'People', 'Room 2'),
(19, 'Occupancy', 'People', 'Room 3'),
(20, 'Occupancy', 'People', 'Room 4'),
(21, 'Occupancy', 'People', 'Room 5');