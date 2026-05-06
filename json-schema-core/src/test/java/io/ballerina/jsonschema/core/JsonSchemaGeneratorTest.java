/*
 * Copyright (c) 2025, WSO2 LLC. (http://www.wso2.com)
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.ballerina.jsonschema.core;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonSchemaGeneratorTest {
    private static final Path RES_DIR = Paths.get("src/test/resources/").toAbsolutePath();
    private static final String JSON_SCHEMA_DIR = "jsonschema";
    private static final String EXPECTED_DIR = "expected";

    @DataProvider(name = "jsonSchemaProvider")
    public Object[][] provideTestPaths() {
        return new Object[][]{
                {"1_simple_int.json", "1_simple_int.bal"},
                {"2_constrained_int.json", "2_constrained_int.bal"},
                {"3_true.json", "3_true.bal"},
                {"4_false.json", "4_false.bal"},
                {"5_invalid_int.json", "5_invalid_int.bal"},
                {"6_constrained_number.json", "6_constrained_number.bal"},
                {"7_enum_with_string.json", "7_enum_with_string.bal"},
                {"8_enum_with_integer.json", "8_enum_with_integer.bal"},
                {"9_null.json", "9_null.bal"},
                {"10_nested_enum.json", "10_nested_enum.bal"},
                {"11_nested_enum_with_type.json", "11_nested_enum_with_type.bal"},
                {"12_nested_enum_with_multiple_types.json", "12_nested_enum_with_multiple_types.bal"},
                {"13_enum_with_number_type.json", "13_enum_with_number_type.bal"},
                {"14_enum_with_const.json", "14_enum_with_const.bal"},
                {"15_invalid_enum_with_const.json", "15_invalid_enum_with_const.bal"},
                {"16_object_const.json", "16_object_const.bal"},
                {"17_constrained_string.json", "17_constrained_string.bal"},
                {"18_no_type.json", "18_no_type.bal"},
                {"19_universal_array.json", "19_universal_array.bal"},
                {"20_array_with_multiple_types.json", "20_array_with_multiple_types.bal"},
                {"21_array_with_constrained_types.json", "21_array_with_constrained_types.bal"},
                {"22_array_with_multiple_elements.json", "22_array_with_multiple_elements.bal"},
                {"23_array_with_multiple_elements.json", "23_array_with_multiple_elements.bal"},
                {"24_array_with_multiple_elements.json", "24_array_with_multiple_elements.bal"},
                {"25_array_with_multiple_elements.json", "25_array_with_multiple_elements.bal"},
                {"26_array_with_multiple_elements.json", "26_array_with_multiple_elements.bal"},
                {"27_array_with_multiple_elements.json", "27_array_with_multiple_elements.bal"},
                {"28_array_with_multiple_elements.json", "28_array_with_multiple_elements.bal"},
                {"29_array_with_multiple_elements.json", "29_array_with_multiple_elements.bal"},
                {"30_array_with_multiple_elements.json", "30_array_with_multiple_elements.bal"},
                {"31_array_with_unique_items.json", "31_array_with_unique_items.bal"},
                {"32_array_with_contains.json", "32_array_with_contains.bal"},
                {"33_array_with_contains.json", "33_array_with_contains.bal"},
                {"34_universal_object.json", "34_universal_object.bal"},
                {"35_empty_record.json", "35_empty_record.bal"},
                {"36_invalid_record.json", "36_invalid_record.bal"},
                {"37_simple_object_with_fields.json", "37_simple_object_with_fields.bal"},
                {"38_simple_object_with_required_fields.json", "38_simple_object_with_required_fields.bal"},
                {"39_additional_properties.json", "39_additional_properties.bal"},
                {"40_additional_properties.json", "40_additional_properties.bal"},
                {"41_unevaluated_properties.json", "41_unevaluated_properties.bal"},
                {"42_dependent_required.json", "42_dependent_required.bal"},
                {"43_dependent_schema.json", "43_dependent_schema.bal"},
                {"44_array_unevaluated_items.json", "44_array_unevaluated_items.bal"},
                {"45_pattern_properties.json", "45_pattern_properties.bal"},
                {"46_pattern_properties.json", "46_pattern_properties.bal"},
                {"47_property_names.json", "47_property_names.bal"},
                {"48_property_names.json", "48_property_names.bal"},
                {"49_property_names.json", "49_property_names.bal"},
                {"50_min_max_properties.json", "50_min_max_properties.bal"},
                {"51_min_max_properties.json", "51_min_max_properties.bal"},
                {"52_default_value.json", "52_default_value.bal"},
                {"53_simple_referencing.json", "53_simple_referencing.bal"},
                {"54_recursive_array_referencing.json", "54_recursive_array_referencing.bal"},
                {"55_recursive_object_referencing.json", "55_recursive_object_referencing.bal"},
                {"57_field_descriptions.json", "57_field_descriptions.bal"},
                {"58_basic_type_description.json", "58_basic_type_description.bal"},
                {"59_array_type_description.json", "59_array_type_description.bal"},
                {"60_title_on_object_and_fields.json", "60_title_on_object_and_fields.bal"},
                {"61_title_on_array_and_items.json", "61_title_on_array_and_items.bal"},
                {"62_title_on_basic_type.json", "62_title_on_basic_type.bal"},
                {"63_comments_and_examples.json", "63_comments_and_examples.bal"},
                {"64_combined_meta_data.json", "64_combined_meta_data.bal"},
                {"65_readonly_fields.json", "65_readonly_fields.bal"},
                {"66_readonly_basic_type.json", "66_readonly_basic_type.bal"},
                {"67_false_readonly_basic_type.json", "67_false_readonly_basic_type.bal"},
                {"68_writeonly_basic_type.json", "68_writeonly_basic_type.bal"},
                {"69_false_writeonly_basic_type.json", "69_false_writeonly_basic_type.bal"},
                {"70_writeonly_fields.json", "70_writeonly_fields.bal"},
                {"71_deprecated_basic_type.json", "71_deprecated_basic_type.bal"},
                {"72_deprecated_fields.json", "72_deprecated_fields.bal"},
                {"73_dynamic_anchors.json", "73_dynamic_anchors.bal"},
                {"74_dynamic_anchors.json", "74_dynamic_anchors.bal"},
                {"75_anchors.json", "75_anchors.bal"},
                {"76_anchors.json", "76_anchors.bal"},
                {"77_referencing_array_items.json", "77_referencing_array_items.bal"},
                {"78_allof.json", "78_allof.bal"},
                {"80_oneof.json", "80_oneof.bal"},
                {"81_not.json", "81_not.bal"},
                {"82_if.json", "82_if.bal"},
                {"83_if_then.json", "83_if_then.bal"},
                {"84_if_else.json", "84_if_else.bal"},
                {"85_if_then_else.json", "85_if_then_else.bal"},
                {"86_multiple_combined.json", "86_multiple_combined.bal"},
                {"87_nested_combining_keywords.json", "87_nested_combining_keywords.bal"},
                {"88_nested_combining_keywords.json", "88_nested_combining_keywords.bal"},
                {"91_relative_nested_ids_anchor.json", "91_relative_nested_ids_anchor.bal"},
                {"92_root_ref_without_explicit_type.json", "92_root_ref_without_explicit_type.bal"}
        };
    }

    @DataProvider(name = "multiJsonSchemaProvider")
    public Object[][] provideMultiJsonTestPaths() {
        return new Object[][]{
                {
                        new String[]{"56_ref_across_files_0.json", "56_ref_across_files_1.json"},
                        "56_ref_across_files.bal"
                }
        };
    }

    @Test(dataProvider = "jsonSchemaProvider")
    public void testJsonSchemaToRecord(String jsonFilePath, String balFilePath) throws Exception {
        validate(RES_DIR.resolve(JSON_SCHEMA_DIR).resolve(jsonFilePath),
                RES_DIR.resolve(EXPECTED_DIR).resolve(balFilePath), new Generator());
    }

    @Test(dataProvider = "multiJsonSchemaProvider")
    public void testMultipleJsonSchemasToSingleRecord(String[] jsonFilePaths, String balFilePath) throws Exception {
        Path expectedPath = RES_DIR.resolve(EXPECTED_DIR).resolve(balFilePath);
        validateMultiple(jsonFilePaths, expectedPath, new Generator());
    }

    private void validate(Path sample, Path expected, Generator generator) throws Exception {
        String jsonSchemaFileContent = Files.readString(sample);
        Object schema = SchemaUtils.parseJsonSchema(jsonSchemaFileContent);
        Response result = generator.convertBaseSchema(new ArrayList<>() {{ add(schema); }});
        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        String expectedValue = Files.readString(expected);
        Assert.assertEquals(result.getTypes(), expectedValue, "Generated types do not match expected output");
    }

    private void validateMultiple(String[] jsonFilePaths, Path expected, Generator generator) throws Exception {
        ArrayList<Object> schemas = new ArrayList<>();
        for (String jsonFile : jsonFilePaths) {
            Path path = RES_DIR.resolve(JSON_SCHEMA_DIR).resolve(jsonFile);
            String content = Files.readString(path);
            Object schema = SchemaUtils.parseJsonSchema(content);
            schemas.add(schema);
        }

        Response result = generator.convertBaseSchema(schemas);

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        String expectedContent = Files.readString(expected);
        Assert.assertEquals(result.getTypes(), expectedContent, "Generated types do not match expected output");
    }

    @Test
    public void testEscapesSpecialCharacterPropertyNamesAndDependentRequired() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "type": "object",
                  "dependentRequired": {
                    "foo\\nbar": ["foo\\rbar"],
                    "foo\\\"bar": ["foo'bar"]
                  }
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);
        Response result = new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }});

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        Assert.assertTrue(result.getTypes().contains("'foo" + "\\" + "u{A}bar"),
                "Expected newline key to use a quoted identifier");
        Assert.assertTrue(result.getTypes().contains("'foo" + "\\" + "u{22}bar"),
                "Expected quote key to use a quoted identifier");
        Assert.assertTrue(result.getTypes().contains("'foo" + "\\" + "u{D}bar"),
                "Expected carriage-return dependent key to use a quoted identifier");
        Assert.assertTrue(result.getTypes().contains("'foo" + "\\" + "u{27}bar"),
                "Expected apostrophe dependent key to use a quoted identifier");
        Assert.assertTrue(result.getTypes().contains("[\"foo\\rbar\"]"),
                "Expected dependentRequired values to be escaped as Ballerina string literals");
        Assert.assertTrue(result.getTypes().contains("[\"foo'bar\"]"),
                "Expected dependentRequired apostrophe values to remain valid string literals");
    }

    @Test
    public void testRejectsEmptyPropertyName() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "type": "object",
                  "properties": {
                    "": { "type": "string" }
                  }
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);

        IllegalArgumentException exception = Assert.expectThrows(IllegalArgumentException.class,
                () -> new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }}));
        Assert.assertTrue(exception.getMessage().contains("non-empty"),
                "Expected a hard failure for empty property names");
    }

    @Test
    public void testGeneratorUtilsEscapesStringsAndComments() {
        Assert.assertEquals(GeneratorUtils.toBallerinaStringLiteral("foo\nbar\"baz\\qux"),
                "\"foo\\nbar\\\"baz\\\\qux\"");
        Assert.assertEquals(GeneratorUtils.toBallerinaFieldIdentifier("foo\nbar"),
                "'foo" + "\\" + "u{A}bar");
        Assert.assertEquals(GeneratorUtils.toBallerinaFieldIdentifier("foo\"bar"),
                "'foo" + "\\" + "u{22}bar");
        Assert.assertEquals(GeneratorUtils.toCommentLines("line1\r\nline2").toString(), "[# line1, # line2]");
    }

    @Test
    public void testDependentSchemasUsesStandardKeyword() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "type": "object",
                  "properties": {
                    "bar": { "type": "integer" }
                  },
                  "dependentSchemas": {
                    "bar": {
                      "type": "object",
                      "properties": {
                        "foo": { "type": "integer" },
                        "bar": { "type": "integer" }
                      }
                    }
                  }
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);
        Response result = new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }});

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        Assert.assertTrue(result.getTypes().contains("@jsondata:DependentSchema"),
                "Expected dependentSchemas to generate the dependent schema annotation");
        Assert.assertTrue(result.getTypes().contains("public type BarDependentSchema record {|"),
                "Expected dependentSchemas to generate the nested dependent schema type");
    }

    @Test
    public void testDependentSchemasEscapesGeneratedTypeNames() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "dependentSchemas": {
                    "foo\\tbar": {"minProperties": 4},
                    "foo'bar": {"required": ["foo\\"bar"]}
                  }
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);
        Response result = new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }});

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        Assert.assertFalse(result.getTypes().contains("Foo 'bar"),
                "Generated type names should not contain apostrophes");
        Assert.assertFalse(result.getTypes().contains("Foo\tbar"),
                "Generated type names should not contain tabs");
        Assert.assertTrue(result.getTypes().contains("Foo_barDependentSchema"),
                "Expected sanitized dependent schema type name");
        Assert.assertTrue(result.getTypes().contains("Foo_barDependentSchema1"),
                "Expected conflicting sanitized names to be disambiguated");
    }

    @Test
    public void testDependentSchemasHoistsUnevaluatedProperties() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "properties": { "foo": { "type": "string" } },
                  "dependentSchemas": {
                    "foo": { "properties": { "bar": { "const": "bar" } }, "required": ["bar"] }
                  },
                  "unevaluatedProperties": false
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);
        Response result = new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }});

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        Assert.assertTrue(result.getTypes().contains("@jsondata:DependentSchema"),
                "Expected dependentSchemas annotations to be preserved");
        Assert.assertTrue(result.getTypes().contains("@jsondata:UnevaluatedProperties"),
                "Expected dependentSchemas to force hoisting of unevaluatedProperties");
        Assert.assertTrue(result.getTypes().contains("json...;"),
                "Expected the structural record rest descriptor to remain open for dependentSchemas");
    }

    @Test
    public void testRefWithSiblingKeywordsIsCombined() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "$defs": {
                    "base": {
                      "type": "array",
                      "items": { "type": "string" }
                    }
                  },
                  "$ref": "#/$defs/base",
                  "minItems": 2
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);
        Response result = new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }});

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        Assert.assertTrue(result.getTypes().contains("@jsondata:AllOf"),
                "Expected $ref with sibling constraints to be rewritten as an allOf combination");
        Assert.assertTrue(result.getTypes().contains("minItems: 2"),
                "Expected sibling constraints to be preserved alongside the referenced schema");
        Assert.assertTrue(result.getTypes().contains("Foo foo?;"),
                "Expected generated property types to use PascalCase named types");
    }

    @Test
    public void testRefSiblingsKeepUnevaluatedPropertiesAtWrapperLevel() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "$ref": "#/$defs/bar",
                  "properties": { "foo": { "type": "string" } },
                  "unevaluatedProperties": false,
                  "$defs": { "bar": { "properties": { "bar": { "type": "string" } } } }
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);
        Response result = new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }});

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        Assert.assertTrue(result.getTypes().contains("@jsondata:UnevaluatedProperties"),
                "Expected unevaluatedProperties to be preserved on the rewritten wrapper");
        Assert.assertFalse(result.getTypes().contains(
                "@jsondata:UnevaluatedProperties {\n    value: never\n}\npublic type SchemaAllOf2Object"),
                "Expected the sibling branch to avoid carrying the unevaluatedProperties annotation itself");
    }

    @Test
    public void testOneOfBranchesMaterializeDistinctAliases() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "oneOf": [{}, {}, {}]
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);
        Response result = new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }});

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        Assert.assertTrue(result.getTypes().contains("public type SchemaOneOf1 json;"),
                "Expected the first oneOf branch to be materialized as a named alias");
        Assert.assertTrue(result.getTypes().contains("public type SchemaOneOf2 json;"),
                "Expected the second oneOf branch to be materialized as a named alias");
        Assert.assertTrue(result.getTypes().contains("public type SchemaOneOf3 json;"),
                "Expected the third oneOf branch to be materialized as a named alias");
        Assert.assertTrue(result.getTypes().contains(
        "public type SchemaSubTypes SchemaOneOf1|SchemaOneOf2|SchemaOneOf3;"),
                "Expected oneOf union members to keep distinct aliases");
    }

    @Test
    public void testArrayRestUnionMaterializesDistinctAliases() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "type": "array",
                  "prefixItems": [{}, {}],
                  "minItems": 1,
                  "items": {}
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);
        Response result = new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }});

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        Assert.assertTrue(result.getTypes().contains("public type SchemaItem1 json;"),
                "Expected overlapping prefix items to be materialized with their stable item alias");
        Assert.assertTrue(result.getTypes().contains("public type SchemaRestItem json;"),
                "Expected items to be materialized with a stable rest-item alias");
        Assert.assertTrue(result.getTypes().contains("prefixItems: [json, SchemaItem1]"),
                "Expected the prefixItems annotation to reuse the same alias as the rest union");
        Assert.assertTrue(result.getTypes().contains("[json, (SchemaItem1|SchemaRestItem)...]"),
                "Expected merged array rest unions to reuse stable aliases");
    }

    @Test
    public void testNotAnnotationAddsJsonDataImport() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "not": { "type": "integer" }
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);
        Response result = new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }});

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        Assert.assertTrue(result.getTypes().contains("import ballerina/data.jsondata;"),
                "Expected @jsondata:Not usage to add the jsondata import");
        Assert.assertTrue(result.getTypes().contains("@jsondata:Not"),
                "Expected the generated type to include the Not annotation");
    }

    @Test
    public void testAdditionalPropertiesTrueAddsExplicitAnnotation() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "type": "object",
                  "additionalProperties": true
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);
        Response result = new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }});

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        Assert.assertTrue(result.getTypes().contains("import ballerina/data.jsondata;"),
                "Expected explicit additionalProperties: true to add the jsondata import");
        Assert.assertTrue(result.getTypes().contains("@jsondata:AdditionalProperties"),
                "Expected explicit additionalProperties: true to add the annotation");
        Assert.assertTrue(result.getTypes().contains("value: json"),
                "Expected explicit additionalProperties: true to be recorded as json");
    }

    @Test
    public void testPatternPropertiesDoesNotInferAdditionalPropertiesAnnotation() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "properties": { "foo": { "type": "string" } },
                  "allOf": [{ "patternProperties": { "^bar": { "type": "string" } } }],
                  "unevaluatedProperties": false
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);
        Response result = new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }});

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        Assert.assertFalse(result.getTypes().contains("@jsondata:AdditionalProperties"),
"Expected patternProperties without explicit additionalProperties to avoid synthesizing the annotation");
        Assert.assertTrue(result.getTypes().contains("@jsondata:PatternProperties"),
"Expected patternProperties annotation to still be present");
        Assert.assertTrue(result.getTypes().contains("@jsondata:UnevaluatedProperties"),
                "Expected hoisted unevaluatedProperties annotation to still be present");
    }

    @Test
    public void testPatternPropertiesWithoutAdditionalPropertiesUsesJsonRestApproximation() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "patternProperties": { "^foo": { "type": "string" } },
                  "unevaluatedProperties": false
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);
        Response result = new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }});

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        Assert.assertTrue(result.getTypes().contains("@jsondata:PatternProperties"),
                "Expected patternProperties annotation to be present");
        Assert.assertTrue(result.getTypes().contains("@jsondata:UnevaluatedProperties"),
                "Expected patternProperties with unevaluatedProperties to preserve the annotation");
        Assert.assertTrue(result.getTypes().contains("json...;"),
                "Expected patternProperties without explicit additionalProperties to use json rest approximation");
        Assert.assertFalse(result.getTypes().contains("string...;"),
                "Expected the structural approximation to avoid implying all extra fields are strings");
    }

    @Test
    public void testNestedAdditionalPropertiesTriggersUnevaluatedPropertiesHoist() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "properties": { "foo": { "type": "string" } },
                  "allOf": [{ "additionalProperties": true }],
                  "unevaluatedProperties": false
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);
        Response result = new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }});

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        Assert.assertTrue(result.getTypes().contains("@jsondata:UnevaluatedProperties"),
                "Expected nested additionalProperties to trigger hoisting of unevaluatedProperties");
        Assert.assertTrue(result.getTypes().contains("@jsondata:AllOf"),
                "Expected the generated type to remain an allOf composition");
    }

    @Test
    public void testNestedUnevaluatedPropertiesTriggersHoistWithoutTypedRestField() throws Exception {
        String jsonSchema = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "properties": { "foo": { "type": "string" } },
                  "allOf": [{ "unevaluatedProperties": true }],
                  "unevaluatedProperties": { "type": "string", "maxLength": 2 }
                }
                """;

        Object schema = SchemaUtils.parseJsonSchema(jsonSchema);
        Response result = new Generator().convertBaseSchema(new ArrayList<>() {{ add(schema); }});

        Assert.assertTrue(result.getDiagnostics().isEmpty(), "Diagnostics should be empty");
        Assert.assertTrue(result.getTypes().contains("@jsondata:AllOf"),
                "Expected the generated type to remain an allOf composition");
        Assert.assertTrue(result.getTypes().contains("@jsondata:UnevaluatedProperties"),
                "Expected explicit unevaluatedProperties to be preserved as annotations");
        Assert.assertFalse(result.getTypes().contains("SchemaMainTypeObjectUnevaluatedProperties...;"),
                "Expected outer unevaluatedProperties to avoid a typed rest-field approximation");
        Assert.assertTrue(result.getTypes().split(
                "@jsondata:UnevaluatedProperties", -1).length > 2,
                "Expected both outer and nested unevaluatedProperties annotations to be preserved");
    }

}
