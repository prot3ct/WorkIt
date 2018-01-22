using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.BLL
{
    public class LocationBussinessLogic
    {
        private WorkItDbContext db;

        public LocationBussinessLogic(WorkItDbContext db)
        {
            this.Db = db;
        }

        public WorkItDbContext Db
        {
            get
            {
                return this.db;
            }
            set
            {
                this.db = value;
            }
        }

        public bool CheckExsistingLocation(Location location)
        {
            if (db.Locations.Any(p => p.Address == location.Address && p.City == location.City && p.Country == location.Country)) 
            {
                return true;
            }
            return false;
        }

        public Location CreateLocation(Location location)
        {
            if (!CheckExsistingLocation(location))
            {
                db.Locations.Add(location);
                db.SaveChanges();
            }

            return location;
        }
    }
}