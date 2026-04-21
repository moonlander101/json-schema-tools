import ballerina/data.jsondata;

@jsondata:ArrayConstraints {
    prefixItems: [SchemaItem0]
}
public type Schema [(SchemaItem0|string|int)...];

public type SchemaItem0 int|float|decimal;
