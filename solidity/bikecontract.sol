pragma solidity ^0.4.0;

contract BikeContract {
    
    struct Bike {
        address owner;
        uint price;
        uint latitude;
        uint longitude;
        string name;
        string specialities;
        uint code;
    }
    
    Bike[] public bikes;
    
    
    event NewBike(Bike bike);

    function addBike(uint _price, uint _latitude, uint _longitude, string _name, string _specialities, uint _code) public {
        Bike memory bike = Bike(msg.sender, _price, _latitude, _longitude, _name, _specialities, _code);
        bikes.push(bike);
        emit NewBike(bike);
    }
}

