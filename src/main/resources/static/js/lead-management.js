const publicVapidKey = 'BHaFurcnCk07ymRP2_TFcNkquDnsLyVtIFcfMXnPyQyjhciDTi57wZlNmjeGUWQgJjSsFCu1UnQXxMaEFnrRUW4'; // URL-safe base64

function urlBase64ToUint8Array(base64String) {
  const padding = '='.repeat((4 - base64String.length % 4) % 4);
  const base64 = (base64String + padding)
    .replace(/-/g, '+')
    .replace(/_/g, '/');

  const rawData = window.atob(base64);
  //console.log('--->>>>>'+Uint8Array.from([...rawData].map(c => c.charCodeAt(0))));
  return Uint8Array.from([...rawData].map(c => c.charCodeAt(0)));
}


async function subscribeUser() {
	//alert("===in subscribeUser==="+publicVapidKey);
  if ('serviceWorker' in navigator) {
    // Register the service worker
    const registration = await navigator.serviceWorker.register('/service-worker.js');

    // Subscribe to push with your app server public key
    const subscription = await registration.pushManager.subscribe({
      userVisibleOnly: true,
      applicationServerKey: urlBase64ToUint8Array(publicVapidKey)
    });

    // Send subscription to backend
    const subData = {
      endpoint: subscription.endpoint,
      p256dh: btoa(String.fromCharCode(...new Uint8Array(subscription.getKey('p256dh')))),
      auth: btoa(String.fromCharCode(...new Uint8Array(subscription.getKey('auth')))),
      deviceType: navigator.userAgent
    };

    await fetch('/user-subscriptions', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(subData)
    });

    console.log('User subscribed for push notifications');
  } else {
    console.warn('Service workers or push notifications are not supported.');
  }
}