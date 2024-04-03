import os
import shutil

def delete_old_artifacts(repo_path):
    for root, dirs, files in os.walk(repo_path):
        for file in files:
            if file.endswith('.pom'):
                pom_path = os.path.join(root, file)
                artifact_dir = os.path.dirname(pom_path)
                versions = [f for f in os.listdir(artifact_dir) if os.path.isdir(os.path.join(artifact_dir, f))]
                versions.sort()
                if len(versions) > 1:
                    for version in versions[:-1]:  # Keep the latest version
                        version_path = os.path.join(artifact_dir, version)
                        print(f"Deleting: {version_path}")
                        shutil.rmtree(version_path)

# Provide the path to your Maven repository (.m2 folder)
repo_path = "/path/to/your/.m2/repository"
delete_old_artifacts(repo_path)
