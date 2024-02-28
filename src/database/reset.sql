DROP SEQUENCE IF EXISTS
    brand_sequence,
    category_sequence,
    transmission_type_sequence,
    engine_type_sequence,
    car_sequence,
    user_sequence,
    review_sequence,
    event_sequence
    CASCADE;

DROP TABLE IF EXISTS 
    Brand, 
    Category, 
    Transmission_Type,
    Engine_Type,
    Car,
    UserInfo,
    Event,
    Review
    CASCADE;