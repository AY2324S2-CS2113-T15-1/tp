#!/usr/bin/env bash

# Change to script directory
cd "${0%/*}"

# Change directory to project root
cd ..

# Build project
./gradlew clean shadowJar

# Change directory to text-ui-test
cd text-ui-test
rm -rf ./data/

# Function to check test result
check_test() {
    local input_file="$1"
    local expected_output="$2"
    local actual_output="$3"
    local test_name="$4"
    local error_count_ref="$5"
    local failed_tests_ref="$6"
    
    # Run the test and generate actual output
    java -jar "$(find ../build/libs/ -mindepth 1 -print -quit)" < "$input_file" > "$actual_output"

    cp "$expected_output.TXT" "$expected_output-UNIX.TXT"
    dos2unix "$expected_output-UNIX.TXT" "$actual_output"
    diff "$expected_output-UNIX.TXT" "$actual_output"
    
    if [ $? -ne 0 ]
    then
        ((error_count_ref++))
        eval "$failed_tests_ref+=\" $test_name\""
    fi
}

check_data() {
    local expected_output="$1"
    local actual_output="$2"
    local test_name="$3"
    local error_count_ref="$4"
    local failed_tests_ref="$5"

    cp "$expected_output.TXT" "$expected_output-UNIX.TXT"
    dos2unix "$expected_output-UNIX.TXT" "$actual_output"
    diff --strip-trailing-cr "$expected_output-UNIX.TXT" "$actual_output"
    
    if [ $? -ne 0 ]
    then
        ((error_count_ref++))
        eval "$failed_tests_ref+=\" $test_name\""
    fi
}

# Initialize error count variable
ERROR_COUNT=0

# Initialize variable to store names of failed tests
FAILED_TESTS=""

# Run tests
check_test "input/input5B.txt" "expected_output/EXPECTED5B" "actual_output/ACTUAL5B.TXT" "5B" ERROR_COUNT FAILED_TESTS
check_test "input/input1.txt" "expected_output/EXPECTED1" "actual_output/ACTUAL1.TXT" "1" ERROR_COUNT FAILED_TESTS
check_test "input/input2.txt" "expected_output/EXPECTED2" "actual_output/ACTUAL2.TXT" "2" ERROR_COUNT FAILED_TESTS
check_test "input/input3.txt" "expected_output/EXPECTED3" "actual_output/ACTUAL3.TXT" "3" ERROR_COUNT FAILED_TESTS
check_test "input/input4.txt" "expected_output/EXPECTED4" "actual_output/ACTUAL4.TXT" "4" ERROR_COUNT FAILED_TESTS
check_test "input/input5A.txt" "expected_output/EXPECTED5A" "actual_output/ACTUAL5A.TXT" "5A" ERROR_COUNT FAILED_TESTS

check_data "expected_data/EXPECTED_MEMBER" "data/GroupA/members.txt" "MEMBER" ERROR_COUNT FAILED_TESTS
check_data "expected_data/EXPECTED_TRANSACTION" "data/GroupA/transactions.txt" "TRANSACTION" ERROR_COUNT FAILED_TESTS
check_data "expected_data/EXPECTED_GRPLIST" "data/groupList.txt" "GRPLIST" ERROR_COUNT FAILED_TESTS
check_data "expected_data/EXPECTED_PIN" "data/pin.txt" "PIN" ERROR_COUNT FAILED_TESTS

# Output test results
if [ $ERROR_COUNT -eq 0 ]; then
    echo "All tests passed!"
else
    echo "$ERROR_COUNT tests failed:$FAILED_TESTS"
fi

# Exit with error count
exit $ERROR_COUNT