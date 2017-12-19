using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WorkItAPI.Models
{
    public class User
    {
        public User() { }

        [Key]
        public int UserID { get; set; }
        public string Name { get; set; }
    }
}