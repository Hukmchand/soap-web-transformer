import os
import re

# Define the directory where your application folders are located
directory = '/path/to/your/config/store'

# Define the new property to add
new_property = 'your.new.property=new_value'

# Regex pattern to match the first line of application.properties files
pattern = re.compile(r'^', re.MULTILINE)

# Iterate through each directory and file
for root, dirs, files in os.walk(directory):
    for file in files:
        if file == 'application.properties':
            file_path = os.path.join(root, file)
            # Read the content of the file
            with open(file_path, 'r') as f:
                content = f.read()
            # Insert the new property at the beginning of the file
            new_content = re.sub(pattern, new_property + '\n', content, count=1)
            # Write the updated content back to the file
            with open(file_path, 'w') as f:
                f.write(new_content)
            print(f'Added property to {file_path}')
