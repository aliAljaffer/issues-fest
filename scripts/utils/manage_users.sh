#!/bin/bash

# User Management Script
# Creates, modifies, and removes system users
# May contain: security issues, validation gaps, race conditions

ACTION=$1
USERNAME=$2
GROUP=$3

usage() {
    echo "Usage: $0 {create|delete|modify} username [group]"
    exit 1
}

if [ $# -lt 2 ]; then
    usage
fi

create_user() {
    local user=$1
    local group=$2

    echo "Creating user: $user"

    # Check if user already exists
    if id "$user" &>/dev/null; then
        echo "User $user already exists"
        exit 1
    fi

    # Create user
    useradd -m -s /bin/bash $user

    # Set initial password
    echo "$user:changeme" | chpasswd

    # Add to group if specified
    if [ ! -z "$group" ]; then
        usermod -aG $group $user
    fi

    # Set password expiry
    chage -d 0 $user

    echo "User $user created successfully"
}

delete_user() {
    local user=$1

    echo "Deleting user: $user"

    # Check if user exists
    if ! id "$user" &>/dev/null; then
        echo "User $user does not exist"
        exit 1
    fi

    # Kill user processes
    pkill -u $user

    # Delete user and home directory
    userdel -r $user

    echo "User $user deleted successfully"
}

modify_user() {
    local user=$1
    local group=$2

    echo "Modifying user: $user"

    if ! id "$user" &>/dev/null; then
        echo "User $user does not exist"
        exit 1
    fi

    if [ ! -z "$group" ]; then
        usermod -aG $group $user
        echo "Added $user to group $group"
    fi
}

case $ACTION in
    create)
        create_user $USERNAME $GROUP
        ;;
    delete)
        delete_user $USERNAME
        ;;
    modify)
        modify_user $USERNAME $GROUP
        ;;
    *)
        usage
        ;;
esac
