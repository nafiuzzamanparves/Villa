| Value       | 	Action                                                      | 	Use Case                     | 	Recommended Environment | 
|-------------|--------------------------------------------------------------|-------------------------------|--------------------------|
| none        | 	No schema modification	                                     | Manual schema management      | 	Production              |
| validate    | 	Validates the schema, errors if differences found	          | Ensure schema consistency	    | Production               |
| update      | 	Updates the schema (adds columns/tables, but not deletions) | 	Incremental schema changes	  | Development              |
| create      | 	Drops and creates the schema	                               | Initial setup, fresh testing	 | Development, Testing     |
| create-drop | 	Creates schema on startup, drops on shutdown	               | Fresh setup for every run	    | Testing                  |
| drop        | 	Drops the schema	                                           | Temporary schema removal	     | Edge-case Testing        |