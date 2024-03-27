import os
import re

# Define the parent directory where your application folders are located
parent_directory = '/path/to/parent/directory'

# List of apps to update
apps_to_update = ['a', 'c']  # Specify the apps to update

# Define the new property to add
new_property = 'your.new.property=new_value'

# Regex pattern to match the first line of application.properties files
pattern = re.compile(r'^', re.MULTILINE)

# Iterate through each directory
for stage in os.listdir(parent_directory):
    stage_path = os.path.join(parent_directory, stage)
    if os.path.isdir(stage_path):
        for app in apps_to_update:
            app_path = os.path.join(stage_path, app)
            properties_file = os.path.join(app_path, 'app.properties')
            if os.path.isfile(properties_file):
                # Read the content of the file
                with open(properties_file, 'r') as f:
                    content = f.read()
                # Insert the new property at the beginning of the file
                new_content = re.sub(pattern, new_property + '\n', content, count=1)
                # Write the updated content back to the file
                with open(properties_file, 'w') as f:
                    f.write(new_content)
                print(f'Added property to {properties_file}')
            else:
                print(f'No app.properties found in {app_path}')
              
