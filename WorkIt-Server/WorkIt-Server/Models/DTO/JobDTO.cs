using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models.DTO
{
    public class JobDTO
    {
        public string Title { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }
        public string Description { get; set; }
        public string Country { get; set; }
        public string City { get; set; }
        public string Address { get; set; }
        public string Reward { get; set; }
        public string CreatorEmail { get; set; }
        public int MinRaiting { get; set; }
        public int MinJobsCompleted { get; set; }

        public Location ToLocation()
        {
            return new Location
            {
                City = this.City,
                Country = this.Country,
                Address = this.Address
            };
        }
    }
}