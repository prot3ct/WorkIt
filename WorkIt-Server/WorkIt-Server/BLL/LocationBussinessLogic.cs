using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.BLL
{
    public class LocationBussinessLogic : BaseService
    {
        public bool CheckExsistingLocation(WorkItDbContext db, Location location)
        {
            if (db.Locations.Any(p => p.Address == location.Address && p.City == location.City && p.Country == location.Country)) 
            {
                return true;
            }
            return false;
        }

        public Location CreateLocation(WorkItDbContext db, Location location)
        {
            if (!CheckExsistingLocation(db, location))
            {
                db.Locations.Add(location);
                db.SaveChanges();
            }

            return location;
        }
    }
}