import ballerina/data.jsondata;

public type Schema record {|
    Data data?;
    json...;
|};

@jsondata:ArrayConstraints {
    prefixItems: [string, DataItem1]
}
public type Data [(string|DataItem1|DataItem1)...];

@jsondata:NumberConstraints {
    minimum: 10.0
}
public type DataItem1 int|float|decimal;
