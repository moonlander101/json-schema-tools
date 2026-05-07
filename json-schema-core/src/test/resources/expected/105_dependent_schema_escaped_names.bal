import ballerina/data.jsondata;

public type SchemaObject record {|
    @jsondata:DependentSchema {
        value: Foo_barDependentSchema1
    }
    json 'foo\u{27}bar?;
    @jsondata:DependentSchema {
        value: Foo_barDependentSchema
    }
    json 'foo\u{9}bar?;
    json...;
|};

@jsondata:ObjectConstraints {
    minProperties: 4
}
public type Foo_barDependentSchemaObject record {|
    json...;
|};

public type Foo_barDependentSchema boolean|string|[json...]|Foo_barDependentSchemaObject|()|int|float|decimal;

public type Foo_barDependentSchema1Object record {|
    json 'foo\u{22}bar;
    json...;
|};

public type Foo_barDependentSchema1 boolean|string|[json...]|Foo_barDependentSchema1Object|()|int|float|decimal;

public type Schema boolean|string|[json...]|SchemaObject|()|int|float|decimal;
