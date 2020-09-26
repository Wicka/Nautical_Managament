package wicka.nautical.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wicka.nautical.management.dto.User;
import wicka.nautical.management.service.IUserService;


@RestController
@RequestMapping ("management/api")
public class UserController {

	@Autowired
	IUserService iUserService;
	
	@GetMapping("/login")
	public HashMap<String,Object> LoginUser (@RequestBody User user){
		
		HashMap<String, Object> map = new HashMap<>();
		
		try {
			for (User userChecking: iUserService.showAllUsers()) {
				 
				  if(userChecking.getEmail().equals(user.getEmail()))
	                {
	                    if(userChecking.getPassword().equals(user.getPassword()))
	                    {
	                        map.put("message", "All correct!");
	                        map.put("email:", user.getEmail());
	                        map.put("type User:", userChecking.getTypeUser());
	                        map.put("success:", true);
	                    }
	                    else
	                    {
	                        map.put("message", "Mail address or Password not correct");
	                        map.put("success:", false);
	                    }
	                }
	                else
	                {
	                    map.put("message", "Mail address or Password not correct");
	                    map.put("success:", false);
	                }
				  
			  }
			
		} catch (Exception e) {
			
	          map.put("message", "something went wrong! :" + e.getMessage());
	          
		}
		return map;
	}
	
	  @GetMapping("/users") // SHOW ALL USERS
	    public List<User> showAllUsers()
	    {
	        return iUserService.showAllUsers();
	    }

	    @GetMapping("/users/{id}") // SHOW USER UNIQUE
	    public User showUserById(@PathVariable Long id)
	    {
	        return iUserService.showUserById(id);
	    }

	    @PostMapping("/users") // CREATE USERS/
	    public HashMap <String, Object> createUsers(@RequestBody User user)
	    
	    {
	       HashMap<String, Object> map = new HashMap<>();

	        try
	        {
	            if(user.getEmail() == null || user.getPassword() == null)
	            {
	                map.put("message", "Please, write an email and password.");
	                map.put("success:", false);
	                //throw new Exception();
	            }
	            else if(user.getEmail().equals("") || user.getPassword().equals(""))
	            {
	                map.put("message", "Please, write an email and password.");
	                map.put("success:", false);
	                //throw new Exception();
	            }
	            else
	            {
	                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
	                Pattern pat = Pattern.compile(emailRegex);

	                if(pat.matcher(user.getEmail()).matches())
	                {
	                    iUserService.saveNewUser(user);
	                    map.put("message:", "All correct!");
	                    map.put("type User:",user.getTypeUser());
	                    map.put("success:", true);
	                }
	                else
	                {
	                    map.put("message", "Please, write a valid email.");
	                    map.put("success:", false);
	                }
	            }
	        }
	        catch(Exception e)
	        {
	            map.put("message", "something went wrong! :" + e.getMessage());
	        }
	        return map;
	    }
	
	    @PostMapping("/users/admins") // CREATE USERS/ADMINS
	    public HashMap <String, Object> createUserAdmin(@RequestBody User user)
	    {
	        HashMap<String, Object> map = new HashMap<>();
	        try
	        {
	            if(user.getEmail() == null || user.getPassword() == null)
	            {
	                map.put("message", "Please, write an email and password.");
	                map.put("success:", false);
	                //throw new Exception();
	            }
	            else if(user.getEmail().equals("") || user.getPassword().equals(""))
	            {
	                map.put("message", "Please, write an email and password.");
	                map.put("success:", false);
	                //throw new Exception();
	            }
	            else
	            {
	                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
	                Pattern pat = Pattern.compile(emailRegex);

	                if(pat.matcher(user.getEmail()).matches())
	                {
	                    iUserService.saveNewAdmin(user);
	                    map.put("message:", "All correct!");
	                    map.put("type User:", user.getTypeUser());
	                    map.put("success:", true);
	                }
	                else
	                {
	                    map.put("message", "Please, write a valid email.");
	                    map.put("success:", false);
	                }
	            }
	        }
	        catch(Exception e)
	        {
	            map.put("message", "something went wrong! :" + e.getMessage());
	        }
	        return map;
	    }

	    @PutMapping("/users/password/{id}") //  USER MODIFY PASSWORD
	    public HashMap <String, Object> modifyUserPass(@PathVariable Long id, @RequestBody User user)
	    {
	        HashMap<String, Object> map = new HashMap<>();
	        try
	        {
	            for (User userChecker: iUserService.showAllUsers())
	            {
	                if(userChecker.getEmail().equals(user.getEmail()))
	                {
	                    if(userChecker.getId() == user.getId())
	                    {
	                        map.put("success:", true);
	                        map.put("User:", user.getEmail());
	                        map.put("message:", "change successful");
	                        iUserService.modifyUserPass(id, user);
	                    }
	                    else
	                    {
	                        map.put("success:",false);
	                        map.put("message:", "Wrong email or id.");
	                    }
	               }
	            }
	        }
	        catch(Exception e)
	        {
	            map.put("message", "something went wrong! :" + e.getMessage());
	        }
	        return map;
	    }

	    @PutMapping("/users/type/{id}") // MODIFY USERS TO CHANGE TO  ADMIN
	    public HashMap <String, Object> modifyTypeUser(@PathVariable Long id, @RequestBody User user)
	    {
	        HashMap<String, Object> map = new HashMap<>();
	        try
	        {
	            for (User userChecker: iUserService.showAllUsers())
	            {
	                if(userChecker.getEmail().equals(user.getEmail()) && userChecker.getId() == user.getId())
	                {
	                    map.put("Email:", user.getEmail());
	                    map.put("Id:", userChecker.getId());
	                    if(userChecker.getTypeUser().equals(user.getTypeUser()))
	                    {
	                        map.put("success:", false);
	                        map.put("message:", "User '" + user.getEmail() + "' have same type actually.");
	                    }
	                    else
	                    {
	                        iUserService.modifyTypeUser(id, user);
	                        map.put("Now User type:", user.getTypeUser());
	                        map.put("success:", true);
	                    }
	                }
	            }
	        }
	        catch(Exception e)
	        {
	            map.put("message", "something went wrong! :" + e.getMessage());
	        }
	        return map;
	    }

	// DELETE

	    @DeleteMapping("/users/{id}") // DELETE USERS ADMIN
	    public void deleteUsers(@PathVariable Long id)
	    {
	        iUserService.deleteUser(id);
	    }
}
