INSERT INTO Brand (name, logo, origin_country) VALUES
    ('Toyota', 'toyota-logo.png', 'Japan'),
    ('Honda', 'honda-logo.png', 'Japan'),
    ('Ford', 'ford-logo.png', 'United States'),
    ('Chevrolet', 'chevrolet-logo.png', 'United States'),
    ('Nissan', 'nissan-logo.png', 'Japan'),
    ('BMW', 'bmw-logo.png', 'Germany'),
    ('Mercedes-Benz', 'mercedes-benz-logo.png', 'Germany'),
    ('Audi', 'audi-logo.png', 'Germany'),
    ('Volkswagen', 'volkswagen-logo.png', 'Germany'),
    ('Hyundai', 'hyundai-logo.png', 'South Korea'),
    ('Kia', 'kia-logo.png', 'South Korea'),
    ('Tesla', 'tesla-logo.png', 'United States');

INSERT INTO Category (name, description) VALUES
    ('Sedan', 'A four-door passenger car with a separate trunk compartment, typically offering comfort and fuel efficiency.'),
    ('SUV', 'A sport utility vehicle combining elements of a road car and an off-road vehicle, known for its versatility and spaciousness.'),
    ('Truck', 'A motor vehicle designed primarily for transporting cargo, offering large capacity and towing capabilities.'),
    ('Minivan', 'A passenger car larger than a sedan, designed for transporting families or groups of people with ample seating and cargo space.'),
    ('Coupe', 'A two-door passenger car with a sloping roofline, often focused on style and performance.'),
    ('Convertible', 'A passenger car with a retractable roof, offering an open-air driving experience.'),
    ('Sports Car', 'A high-performance car designed for speed, handling, and agility, typically with powerful engines and sporty styling.'),
    ('Luxury Car', 'A premium car offering comfort, convenience, advanced features, and high-quality materials, often associated with prestigious brands.'),
    ('Electric Car', 'A car powered by an electric motor and battery, offering zero tailpipe emissions and quiet operation.'),
    ('Hybrid Car', 'A car that combines a gasoline engine with an electric motor for improved fuel efficiency and reduced emissions.');

INSERT INTO Transmission_Type (name, description) VALUES
    ('Automatic', 'Transmission shifts gears automatically without driver input, offering convenience and ease of use, especially in stop-and-go traffic.'),
    ('Manual', 'Driver shifts gears manually using a clutch pedal and gear shifter, providing more control and engagement for driving enthusiasts, but requiring more skill.'),
    ('Continuously Variable Transmission (CVT)', 'Uses pulleys and belts to provide a smooth, seamless gear change, often found in smaller cars and hybrids due to fuel efficiency benefits.'),
    ('Dual-Clutch Transmission (DCT)', 'Uses two clutches for faster, more responsive gear changes than traditional automatics, often found in performance cars and SUVs, but with higher complexity and cost.'),
    ('Automated Manual Transmission (AMT)', 'Similar to a manual transmission but automates gear changes based on driver input or pre-programmed settings, typically less smooth and responsive than other automatics and found in budget-friendly vehicles.');

INSERT INTO Engine_Type (name, description) VALUES
    ('Gasoline', 'Most common type, powered by burning gasoline and air. Affordable and readily available fuel, but emits CO2 and other pollutants.'),
    ('Diesel', 'More efficient than gasoline, offering greater torque and fuel economy, but produces more particulate matter emissions. Higher maintenance costs.'),
    ('Hybrid', 'Combines electric motor with gasoline engine for improved fuel efficiency and reduced emissions. More complex technology, higher upfront cost.'),
    ('Plug-in Hybrid (PHEV)', 'Offers both electric and gasoline modes, can be charged externally for longer electric range. Higher upfront cost, complex technology.'),
    ('Electric', 'Powered solely by electricity, zero emissions, quiet operation. Limited range, long charging times, higher upfront cost.'),
    ('Hydrogen', 'Powered by hydrogen fuel cells, zero emissions, quick refueling. Limited hydrogen station infrastructure, high technology cost.'),
    ('Natural Gas', 'Produces fewer emissions than gasoline, cheaper fuel costs, but limited availability of fueling stations. Requires specific engine conversion.');

INSERT INTO Car (name, year, price, seating_capacity, image, brand_id, transmission_type_id, category_id, engine_type_id) VALUES
    ('Toyota Corolla', 2024, 21990.00, 5, 'toyota-corolla-img.png', 'BRA0001', 'TRT0001', 'CAT0001', 'ENT0001'),
    ('Honda Civic', 2024, 22255.00, 5, 'honda-civic-img.png', 'BRA0002', 'TRT0001', 'CAT0001', 'ENT0001'),
    ('Ford F-150', 2024, 34045.00, 6, 'ford-f150-img.png', 'BRA0003', 'TRT0002', 'CAT0003', 'ENT0002'),
    ('Chevrolet Silverado 1500', 2024, 34295.00, 5, 'chevrolet-silverado-1500-img.png', 'BRA0004', 'TRT0002', 'CAT0003', 'ENT0002'),
    ('Nissan Altima', 2024, 25600.00, 5, 'nissan-altima-img.png', 'BRA0005', 'TRT0001', 'CAT0001', 'ENT0001'),
    ('BMW X3', 2024, 41500.00, 5, 'bmw-x3-img.png', 'BRA0006', 'TRT0001', 'CAT0002', 'ENT0001'),
    ('Mercedes-Benz C-Class', 2024, 44500.00, 5, 'mercedes-benz-c-class-img.png', 'BRA0007', 'TRT0001', 'CAT0001', 'ENT0001'),
    ('Audi A4', 2024, 43445.00, 5, 'audi-a4-img.png', 'BRA0008', 'TRT0001', 'CAT0001', 'ENT0001'),
    ('Hyundai Kona', 2024, 20400.00, 5, 'hyundai-kona-img.png', 'BRA0010', 'TRT0001', 'CAT0002', 'ENT0001'),
    ('Kia Soul', 2024, 20990.00, 5, 'kia-soul-img.png', 'BRA0011', 'TRT0001', 'CAT0002', 'ENT0001'),
    ('Tesla Model 3', 2024, 46990.00, 5, 'tesla-model-3-img.png', 'BRA0012', 'TRT0001', 'CAT0001', 'ENT0005'),
    ('Toyota Prius', 2024, 27450.00, 5, 'toyota-prius-img.png', 'BRA0001', 'TRT0001', 'CAT0001', 'ENT0003'),
    ('Honda Accord', 2024, 26700.00, 5, 'honda-accord-img.png', 'BRA0002', 'TRT0001', 'CAT0001', 'ENT0001'),
    ('Ford Mustang', 2024, 29145.00, 4, 'ford-mustang-img.png', 'BRA0003', 'TRT0002', 'CAT0005', 'ENT0001'),
    ('Chevrolet Camaro', 2024, 29295.00, 4, 'chevrolet-camaro-img.png', 'BRA0004', 'TRT0002', 'CAT0005', 'ENT0001'),
    ('Nissan Frontier', 2024, 27545.00, 5, 'nissan-frontier-img.png', 'BRA0005', 'TRT0002', 'CAT0003', 'ENT0002'),
    ('Toyota RAV4', 2024, 26945.00, 5, 'toyota-rav4-img.png', 'BRA0001', 'TRT0001', 'CAT0002', 'ENT0001'),
    ('Honda CR-V', 2024, 28000.00, 5, 'honda-cr-v-img.png', 'BRA0002', 'TRT0001', 'CAT0002', 'ENT0001'),
    ('BMW X5', 2024, 63350.00, 7, 'bmw-x5-img.png', 'BRA0006', 'TRT0001', 'CAT0002', 'ENT0001'),
    ('Mercedes-Benz GLE-Class', 2024, 69950.00, 5, 'mercedes-benz-gle-class-img.png', 'BRA0007', 'TRT0001', 'CAT0002', 'ENT0001');


