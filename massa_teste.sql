USE javafxSellerDB;

INSERT INTO department (nm_department) VALUES ("Computers"), ("Toys"), ("Games"), ("Movies");

INSERT INTO seller (nm_email, nm_seller, dt_birth, vl_salary, cd_department) VALUES
('john.doe@computers.com', 'John Doe', '1985-05-15', 50000.00, 1),
('jane.smith@computers.com', 'Jane Smith', '1990-07-22', 52000.00, 1),
('alex.jones@toys.com', 'Alex Jones', '1988-03-11', 48000.00, 2),
('emma.brown@toys.com', 'Emma Brown', '1992-09-30', 47000.00, 2),
('mike.wilson@games.com', 'Mike Wilson', '1987-11-05', 51000.00, 3),
('sophia.davis@games.com', 'Sophia Davis', '1993-04-17', 53000.00, 3),
('chris.miller@movies.com', 'Chris Miller', '1986-12-25', 49000.00, 4),
('olivia.garcia@movies.com', 'Olivia Garcia', '1991-06-14', 50000.00, 4);