# Filename: git-auto-commit.ps1

# ----------------------------------------
# Prompt user to enter a commit message
# ----------------------------------------
$commitMessage = Read-Host "Enter commit message"

# ----------------------------------------
# Pull latest changes from remote
# ----------------------------------------
Write-Host "`n[Pull] Pulling latest changes from remote..."
git pull

# ----------------------------------------
# Stage all changes in the repository
# ----------------------------------------
Write-Host "`n[Add] Adding all changes..."
git add .

# ----------------------------------------
# Commit staged changes with the user-provided message
# ----------------------------------------
Write-Host "`n[Commit] Committing changes with message: '$commitMessage'"
git commit -m "$commitMessage"

# ----------------------------------------
# Push the commit to the remote repository
# ----------------------------------------
Write-Host "`n[Push] Pushing changes to remote..."
git push

# ----------------------------------------
# Done!
# ----------------------------------------
Write-Host "`n Git operations completed successfully!"
