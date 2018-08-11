pragma solidity ^0.4.0;

contract Bike {
    address owner;
    uint price;
    ufixed latitude;
    ufixed longitude;
    string name;
    string specialities;
    uint code;

    constructor(uint _price, string _latitude, string _longitude, string _name, string _specialities, uint _code) public {
        owner = msg.sender;
        price = _price;
        latitude = _latitude;
        longitude = _longitude;
        name = _name;
        specialities = _specialities;
        code = _code;
    }
}