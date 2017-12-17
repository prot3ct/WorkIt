using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WorkItDataAccess.Models
{
    public class Job
    {
        public int JobID { get; set; }
        public string Title { get; set; }

        public User Creator { get; set; }
    }
}
