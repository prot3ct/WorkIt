using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models;

namespace WorkIt_Server.BLL
{
    public class PlaceBussinessLogic : BussinessLogicBase
    {
        public bool CheckExsistingPlace(Place place)
        {
            if (WorkItDb.Places.Any(p => p.Address == place.Address && p.City == place.City && p.Country == place.Country)) 
            {
                return true;
            }
            return false;
        }

        public int GetPlaceId(Place place)
        {
            return WorkItDb.Places.FirstOrDefault(p => p.Address == place.Address && p.City == place.City && p.Country == place.Country).PlaceId;
        }
    }
}