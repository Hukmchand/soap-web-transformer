import os
import shutil

def delete_old_artifacts(repo_path):
    for root, dirs, files in os.walk(repo_path):
        for file in files:
            if file.endswith('.pom'):
                pom_path = os.path.join(root, file)
                artifact_dir = os.path.dirname(root)
                versions = [f for f in os.listdir(artifact_dir) if os.path.isdir(os.path.join(artifact_dir, f))]
                versions = [v for v in versions if not v.endswith("-SNAPSHOT")]
                versions.sort()
                if len(versions) > 1:
                    print("Keeping latest version for artifact:", artifact_dir)
                    print("Latest version:", versions[-1])
                    for version in versions[:-1]:  # Delete older versions
                        version_path = os.path.join(artifact_dir, version)
                        print("Deleting:", version_path)
                        shutil.rmtree(version_path)

# Provide the path to your Maven repository (.m2 folder)
repo_path = "/home/hgyanchand/.m2/repository"
delete_old_artifacts(repo_path)
  
