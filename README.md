# ClearLag
This plugin allows servers to automatically remove entities every 10 minutes.  This loop time can be changed.
You can also configure all of the logged messages, specify entities that won't be removed by the plugin, and configure a few more flags about what might make an entity exempt from being removed.

PlaceholderAPI is required by this plugin.

[![modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg)](https://modrinth.com/plugin/clearlag-turbojax)

## Commands:
### /clearlag help
Prints the help message for the command.

### /clearlag now
Triggers an immediate clearing of entities, regardless of what the timer says.

### /clearlag reload
Loads the configs from the file into the plugin.

### /clearlag time
Prints out the time until the next clear in seconds.


## Permissions:
### clearlag.command:
Allows users to use the clearlag command.  Given to all players.

### clearlag.command.now:
Allows users to run "/clearlag now".  Given to operators.

### clearlag.command.time:
Allows users to run "/clearlag time".  Given to all players.

### clearlag.command.reload:
Allows users to run "/clearlag reload".  Given to operators.

### clearlag.command.help:  
Allows users to run "/clearlag help".  Given to all players.
